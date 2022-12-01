package org.springframework.samples.petclinic.game;

import java.util.List;
import java.util.Optional;

import org.springframework.samples.petclinic.player.Player;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/games")
public class GameController {

    private static final String ERROR = "/error";
    private static final String MESSAGE= "message";
    private static final String GAME_NOT_FOUND = "Game not found";

    private GameService gameService;

    @GetMapping(path = "/new")
    public String createGame(ModelMap modelMap) { 
      
        return null;
    }

    @GetMapping(path = "/{code}/lobby")
    public String lobby(ModelMap modelMap){
       
        return null;
    }

    @GetMapping(path="/{id}")
    public String viewGame(@PathVariable("id") Integer gameId, ModelMap modelMap){
        String view = "game/playing";
        Optional<Game> optGame = gameService.findGameById(gameId);
        if(optGame.isPresent()){
            Game game = optGame.get();
            List<Player> players = game.getPlayers();
        }else{
            modelMap.addAttribute(MESSAGE, GAME_NOT_FOUND);
            view= ERROR;
        }
        return null;
    }
    

}