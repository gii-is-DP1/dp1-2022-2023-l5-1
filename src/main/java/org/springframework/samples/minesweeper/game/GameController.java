package org.springframework.samples.minesweeper.game;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.samples.minesweeper.audit.Audit;
import org.springframework.samples.minesweeper.audit.AuditService;
import org.springframework.samples.minesweeper.board.Board;
import org.springframework.samples.minesweeper.board.BoardService;
import org.springframework.samples.minesweeper.board.DifficultyLevel;
import org.springframework.samples.minesweeper.customComponents.PaginatingUtil;
import org.springframework.samples.minesweeper.user.User;
import org.springframework.samples.minesweeper.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;

@Controller
public class GameController {

    private static final String VIEWS_GAMES ="games/menu";
    private static final String VIEWS_PLAY_GAME="games/playNewGame";
    private static final String VIEWS_GAME_RULES="games/gameRules";
    private static final String VIEWS_END_GAME="games/endGame";
    private static final String VIEWS_EXCESSIVE_GAMING="games/excessiveGame";
    private static final String VIEWS_GAMES_LIST="games/listGame";

    private  GameService gameService;

    private  BoardService boardService;

    private  UserService userService;

    private AuditService auditService;

    private PaginatingUtil paginatingUtil;

    @Autowired
	public GameController(GameService clinicService, BoardService clinicService2, UserService clinicService3, AuditService clinicService4, PaginatingUtil paginatingUtil) {
		this.gameService = clinicService;
		this.boardService = clinicService2;
		this.userService = clinicService3;
        this.auditService = clinicService4;
        this.paginatingUtil=paginatingUtil;
	}

    @GetMapping(value="/games")
    public ModelAndView gameMenu(Board board, HttpServletRequest request){
        Principal player = request.getUserPrincipal();
        String name = player.getName();
        Integer recentGames = gameService.getRecentGamesByUsername(name);
        String url = VIEWS_GAMES;
        User user = userService.findUser(name).get();
        if(recentGames==2 && !user.isHardcoregamer()) {
            url = VIEWS_EXCESSIVE_GAMING;
        }
        return new ModelAndView(url);
    }

    @GetMapping(value="/gameRules")
    public ModelAndView gameRules(){
        return new ModelAndView(VIEWS_GAME_RULES);
    }

    @GetMapping(value="/games/endGame")
    public String createGame(@RequestParam String id, @RequestParam String success, @RequestParam String audit_id,Map<String, Object> model) {
        Integer gameId = Integer.valueOf(id);
        Boolean gameSuccess = Boolean.parseBoolean(success);
        Game game = gameService.getGameById(gameId);
        if(game.getInProgress()==false) {
            return "redirect:/oups";
        }
        game.setEnd(LocalDateTime.now());
        game.setInProgress(false);
        game.setSuccess(gameSuccess);
        this.gameService.saveGame(game);
        model.put("success", gameSuccess);
        Integer auditId = Integer.valueOf(audit_id);
        Audit audit = auditService.findAuditById(auditId).get();
        audit.setInProgress(false);
        audit.setSuccess(gameSuccess);
        audit.setEndDate(LocalDateTime.now());
        auditService.save(audit);
        return VIEWS_END_GAME;
    }
    
    @GetMapping(value="/games/playNewGame")
    public String newGame(@RequestParam(value = "difficulty", required = false) String difficulty, Board formBoard, HttpServletRequest req, Map<String, Object> model){
        Principal player = req.getUserPrincipal();
        String name = player.getName();
        User user = userService.findUser(name).get();
        Game oldGame = this.gameService.getActiveGameByUsername(user.getUsername());
        if(oldGame != null) {
            this.gameService.deleteGame(oldGame);
        }
        Audit oldAudit = this.auditService.findActiveAuditByUsername(user.getUsername());
        if(oldAudit != null) {
            this.auditService.deleteAudit(oldAudit);
        }
        DifficultyLevel lv;
        Board board=null;
        if(difficulty.equals("Beginner")){
            lv = DifficultyLevel.BEGGINER;
            board = boardService.boardInit(lv, name);
        }else if(difficulty.equals("Intermediate")){
            lv = DifficultyLevel.INTERMEDIATE;
            board = boardService.boardInit(lv, name);
        }else if(difficulty.equals("Advanced")){
            lv = DifficultyLevel.ADVANCED;
            board = boardService.boardInit(lv, name);
        } else {
            lv = DifficultyLevel.CUSTOM;
            board = boardService.boardInit(formBoard.getRows(),formBoard.getColumns(),formBoard.getMinesNumber(),name);
        }
        Pair<List<String>,Boolean> pair = gameService.initializeSquares(board);
        List<String> squares = pair.getFirst();
        Boolean error = pair.getSecond();
        LocalDateTime date = LocalDateTime.now();
        Game game = new Game();
        game.setStart(date);
        game.setInProgress(true);
        game.setUser(user);
        game.setDifficulty(lv);
        this.gameService.saveGame(game);
        Audit audit = new Audit();
        audit.setStartDate(date);
        audit.setUser(user);
        audit.setInProgress(true);
        audit.setDifficulty(lv);
        this.auditService.save(audit);
        model.put("hardcore", user.isHardcoregamer());
        model.put("error", error);
        model.put("mines", squares);
        model.put("board", board);
        model.put("game", game);
        model.put("difficulty", lv);
        model.put("audit",audit);
        return VIEWS_PLAY_GAME;
    }

    @GetMapping(value = { "/games/activeGames" })
	public String showActiveGamesList(Map<String, Object> model,@PageableDefault(page = 0, size = 5)Pageable pageable) {
		Boolean isActive = true;
		String type = "game";
		this.paginatingUtil.prepareModelForPagination(model, pageable, type, isActive);
        model.put("active",isActive);
		return VIEWS_GAMES_LIST;
	}
    
    @GetMapping(value = { "/games/finishGames" })
	public String showFinishGamesList(Map<String, Object> model, @PageableDefault(page = 0, size = 5)Pageable pageable) {
		Boolean isActive = false;
		String type = "game";
		this.paginatingUtil.prepareModelForPagination(model, pageable, type, isActive);
        model.put("active",isActive);
		return VIEWS_GAMES_LIST;
	}
}
