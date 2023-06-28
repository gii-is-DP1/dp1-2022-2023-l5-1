package org.springframework.samples.petclinic.game;

import java.security.Principal;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.board.Board;
import org.springframework.samples.petclinic.board.BoardService;
import org.springframework.samples.petclinic.board.DifficultyLevel;
import org.springframework.samples.petclinic.square.SquareService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GameController {

    private static final String VIEWS_GAMES ="games/menu";
    private static final String VIEWS_PLAY_GAME="games/playNewGame";
    private static final String VIEWS_GAME_RULES="games/gameRules";
    private static final String VIEWS_WIN_GAME="games/winGame";
    private static final String VIEWS_LOSE_GAME="games/loseGame";

    @Autowired
    private  SquareService squareService;
    @Autowired
    private  BoardService boardService;

    @GetMapping(value="/games")
    public ModelAndView gameMenu(Board board, HttpServletRequest request){
        return new ModelAndView(VIEWS_GAMES);
    }

    @GetMapping(value="/gameRules")
    public ModelAndView gameRules(){
        return new ModelAndView(VIEWS_GAME_RULES);
    }

    @GetMapping(value="/games/winGame")
    public ModelAndView winGame(){
        return new ModelAndView(VIEWS_WIN_GAME);
    }

    @GetMapping(value="/games/loseGame")
    public ModelAndView loseGame(){
        return new ModelAndView(VIEWS_LOSE_GAME);
    }
    
    @GetMapping(value="/games/playNewGame")
    public String newGame(@RequestParam(value = "difficulty", required = false) String difficulty, Board formBoard, HttpServletRequest req, Map<String, Object> model){
        Principal player = req.getUserPrincipal();
        String name = player.getName();

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
        List<String> squares = squareService.initializeSquares(board);
        model.put("mines", squares);
        model.put("board", board);
        return VIEWS_PLAY_GAME;
    }
}
