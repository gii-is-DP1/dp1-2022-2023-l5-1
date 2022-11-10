package org.springframework.samples.petclinic.game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.player.Player;
import org.springframework.samples.petclinic.player.PlayerService;
import org.springframework.samples.petclinic.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javassist.NotFoundException;

@Controller
@RequestMapping("/games")
public class GameController {

    private GameService gameService;
    private PlayerService playerService;

    private UserService userService;
    private String viewGames = "redirect:/games/";
    private static final String VIEWS_EXCEPTION = "exception";

    @Autowired
    public GameController(GameService gameService, PlayerService playerService, UserService userService) {
        this.gameService = gameService;
        this.playerService = playerService;
        this.userService = userService;
    }

    
    @GetMapping()
    public String gameListNotFinished(ModelMap modelMap) {
        String view = "games/gamesList";
        Iterable<Game> games = gameService.findAllNotFinished();
        modelMap.addAttribute("games", games);
        return view;
    }

    @GetMapping("/finished")
    public String gameListFinished(ModelMap modelMap) {
        String view = "games/gamesListFinished";
        Iterable<Game> games = gameService.findAllFinished();
        modelMap.addAttribute("games", games);
        return view;
    }

    @GetMapping("/{gameId}/finished")
    public String gameFinished(ModelMap modelMap, @PathVariable("gameId") int gameId) {
        String view = "games/gameFinished";
        try {
            Game game = gameService.findGameById(gameId);
            Iterable<Player> players = game.getPlayers();
            modelMap.addAttribute("players", players);
            modelMap.addAttribute("game", game);
        } catch (NotFoundException e) {
            log.warn(e.toString());
            return VIEWS_EXCEPTION;
        }
        return view;
    }


}