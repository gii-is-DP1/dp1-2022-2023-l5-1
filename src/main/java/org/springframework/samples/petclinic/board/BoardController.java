package org.springframework.samples.petclinic.board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.game.Game;
import org.springframework.samples.petclinic.game.GameService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/boards")
public class BoardController {

    @Autowired
    private BoardService boardService;

    @Autowired
    private GameService gameService;
 

    @GetMapping(path = "/{code}/init")
    public String init(@PathVariable("code") String code, ModelMap modelMap){      

        Game game = gameService.findGamesByRoomCode(code).iterator().next();
        return boardService.initBoard(game);

    }




}
