package org.springframework.samples.petclinic.game;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.player.PlayerService;
import org.springframework.samples.petclinic.user.AuthoritiesService;
import org.springframework.samples.petclinic.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/games")
public class GameController {
   
    private static final String VIEWS_NEW_GAME = "games/createNewGame";
	private static final String VIEWS_JOIN_GAME = "games/joinGame";

    private PlayerService playerService;

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

	@GetMapping("/joinGame")
	public String joinGame(Map<String, Object> model) {
		Game game = new Game();
		model.put("joinGame", game);
		return VIEWS_JOIN_GAME;
	}
}
