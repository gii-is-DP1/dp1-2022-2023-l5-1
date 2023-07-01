package org.springframework.samples.minesweeper.game;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.samples.minesweeper.board.Board;
import org.springframework.samples.minesweeper.board.BoardService;
import org.springframework.samples.minesweeper.board.DifficultyLevel;
import org.springframework.samples.minesweeper.user.Authorities;
import org.springframework.samples.minesweeper.user.User;
import org.springframework.samples.minesweeper.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GameController {

    private static final String VIEWS_GAMES ="games/menu";
    private static final String VIEWS_PLAY_GAME="games/playNewGame";
    private static final String VIEWS_GAME_RULES="games/gameRules";
    private static final String VIEWS_END_GAME="games/endGame";
    private static final String VIEWS_GAMES_LIST="games/listGame";

    private  GameService gameService;

    private  BoardService boardService;

    private  UserService userService;

    @Autowired
	public GameController(GameService clinicService, BoardService clinicService2, UserService clinicService3) {
		this.gameService = clinicService;
		this.boardService = clinicService2;
		this.userService = clinicService3;
	}

    @GetMapping(value="/games")
    public ModelAndView gameMenu(Board board, HttpServletRequest request){
        return new ModelAndView(VIEWS_GAMES);
    }

    @GetMapping(value="/gameRules")
    public ModelAndView gameRules(){
        return new ModelAndView(VIEWS_GAME_RULES);
    }

    @GetMapping(value="/games/endGame")
    public String createGame(@RequestParam String id, @RequestParam String success, Map<String, Object> model) {
        Integer gameId = Integer.valueOf(id);
        Boolean gameSuccess = Boolean.parseBoolean(success);
        Game game = gameService.getGameById(gameId);
        game.setInProgress(false);
        game.setSuccess(gameSuccess);
        this.gameService.saveGame(game);
        model.put("success", gameSuccess);
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
        Board board=null;
        if(difficulty.equals("Beginner")){
            DifficultyLevel lv = DifficultyLevel.BEGGINER;
            board = boardService.boardInit(lv, name);
        }else if(difficulty.equals("Intermediate")){
            DifficultyLevel lv = DifficultyLevel.INTERMEDIATE;
            board = boardService.boardInit(lv, name);
        }else if(difficulty.equals("Advanced")){
            DifficultyLevel lv = DifficultyLevel.ADVANCED;
            board = boardService.boardInit(lv, name);
        }else if(difficulty.equals("Custom")){
            board = boardService.boardInit(formBoard.getRows(),formBoard.getColumns(),formBoard.getMinesNumber(),name);
        }
        Pair<List<String>,Boolean> pair = gameService.initializeSquares(board);
        List<String> squares = pair.getFirst();
        Boolean error = pair.getSecond();
        boardService.save(board);
        Game game = new Game();
        game.setInProgress(true);
        game.setUser(user);
        this.gameService.saveGame(game);
        model.put("error", error);
        model.put("mines", squares);
        model.put("board", board);
        model.put("game", game);
        return VIEWS_PLAY_GAME;
    }

    @GetMapping(value = { "/games/activeGames" })
	public String showActiveGamesList(Map<String, Object> model) {
		
		List<Game> games = new ArrayList<>(this.gameService.getActiveGames());

		model.put("games", games);
        model.put("active",true);
		return VIEWS_GAMES_LIST;
	}
    @GetMapping(value = { "/games/finishGames" })
	public String showFinishGamesList(Map<String, Object> model) {
		
		List<Game> games = new ArrayList<>(this.gameService.getFinishGames());

		model.put("games", games);
        model.put("active",false);
		return VIEWS_GAMES_LIST;
	}
}
