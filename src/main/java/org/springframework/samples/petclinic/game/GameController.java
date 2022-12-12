package org.springframework.samples.petclinic.game;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.deck.Deck;
import org.springframework.samples.petclinic.player.Player;
import org.springframework.samples.petclinic.player.PlayerService;
import org.springframework.samples.petclinic.user.AuthoritiesService;
import org.springframework.samples.petclinic.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/games")
public class GameController {

    private static final String ERROR = "/error";
    private static final String MESSAGE= "message";
    private static final String GAME_NOT_FOUND = "Game not found";
    private static final String GAME="game";
    private static final String PLAYERS="players";
    private static final String DECK="deck";
    private static final String VIEWS_NEW_GAME = "games/createNewGame";
	private static final String VIEWS_JOIN_GAME = "games/joinGame";
	private GameRepository gameRepository;
    private PlayerService playerService;
    private GameService gameService;


    @GetMapping(path="/{id}")
    public String viewGame(@PathVariable("id") Integer gameId, ModelMap modelMap){
        String view = "games/ongoingGame";
        Optional<Game> optGame = gameService.findGameById(gameId);
        if(optGame.isPresent()){
            Game game = optGame.get();
            modelMap.put(GAME, game);
            Deck deck=game.getDeck();
            modelMap.put(DECK, deck);
            List<Player> players = gameRepository.findPlayersByGameId(gameId);
            modelMap.put(PLAYERS, players);
            
        }else{
            modelMap.addAttribute(MESSAGE, GAME_NOT_FOUND);
            view= ERROR;
        }
        return view;
    }
	@Autowired
	public GameController(PlayerService playerService, UserService userService, AuthoritiesService authoritiesService) {
		this.playerService = playerService;
	}

    @GetMapping("/newGame")
	public String initNewGame(Map<String, Object> model) {
		Game game = new Game();
		model.put("game", game);
		return VIEWS_NEW_GAME;
	}
}