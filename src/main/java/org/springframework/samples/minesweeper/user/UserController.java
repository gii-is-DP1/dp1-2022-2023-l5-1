/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.minesweeper.user;

import java.net.URL;
import java.security.Principal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.minesweeper.audit.Audit;
import org.springframework.samples.minesweeper.audit.AuditService;
import org.springframework.samples.minesweeper.customComponents.PaginatingUtil;
import org.springframework.samples.minesweeper.game.Game;
import org.springframework.samples.minesweeper.game.GameService;
import org.springframework.samples.minesweeper.genre.Genre;
import org.springframework.samples.minesweeper.genre.GenreService;
import org.springframework.samples.minesweeper.platform.Platform;
import org.springframework.samples.minesweeper.platform.PlatformService;
import org.springframework.samples.minesweeper.saga.Saga;
import org.springframework.samples.minesweeper.saga.SagaService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;



@Controller
public class UserController {

	private static final String VIEWS_USER_CREATE_FORM = "users/createUserForm";
  	private static final String VIEWS_USER_LIST = "users/listUser";
	private static final String VIEWS_USER_PROFILE = "users/viewProfile";
	



	private final UserService userService;
	private final AuthoritiesService authoService;
	private final GameService gameService;
	private final SagaService sagaService;
	private final GenreService genreService;
	private final PlatformService platformService;
	private final AuditService auditService;
	private final PaginatingUtil paginatingUtil;

	
	@ModelAttribute("sagas")
	public Collection<Saga> populateSagas() {
		return this.sagaService.findSagas();
	}
	@ModelAttribute("genres")
	public Collection<Genre> populateGenres() {
		return this.genreService.findGenres();
	}
	@ModelAttribute("platforms")
	public Collection<Platform> populatePlatforms() {
		return this.platformService.findPlatforms();
	}

	

	@Autowired
	public UserController(UserService us,AuthoritiesService as,GameService gs, SagaService ss,
		GenreService gens, PlatformService ps, AuditService ads, PaginatingUtil pu) {
		this.userService = us;
		this.authoService = as;
		this.gameService = gs;
		this.sagaService = ss;
		this.genreService = gens;
		this.platformService = ps;
		this.auditService = ads;
		this.paginatingUtil = pu;
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	@GetMapping(value = "/users/new")
	public String initCreationForm(Map<String, Object> model) {
		User user = new User();
		model.put("user", user);
		return VIEWS_USER_CREATE_FORM;
	}

	@PostMapping(value = "/users/new")
	public String processCreationForm(@Valid User user, BindingResult result) {
		if(!user.getProfilePicture().equals("")){
			try {
				URL url = new URL(user.getProfilePicture());
				BufferedImage image = ImageIO.read(url);
				if(image==null){
					result.addError(new ObjectError("profilePicture", "The URL doesn't correspond to a valid image"));
				}
			} catch (IOException e) {
				result.addError(new ObjectError("profilePicture", "Error al leer la URL de la imagen"));
			}
		}

		if (result.hasErrors()) {
			return VIEWS_USER_CREATE_FORM;
		}
		else {
			//creating user, user, and authority
			user.setEnabled(true);
			Authorities authorities= new Authorities();
			authorities.setUser(user);
			authorities.setAuthority("player");
			this.userService.saveUser(user);
			this.authoService.saveAuthorities(authorities);
			return "redirect:/";
		}
	}	

	@GetMapping(value = "/users/update")
	public String initUpdateForm(Map<String, Object> model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Object principal = authentication.getPrincipal();
		UserDetails userDetails = (UserDetails) principal;
		User user = this.userService.findUser(userDetails.getUsername()).orElse(null);
		model.put("user", user);
		return VIEWS_USER_CREATE_FORM;
	}
	@PostMapping(value = "/users/update")
	public String processUpdateForm(@Valid User user, BindingResult result) {
		if(!user.getProfilePicture().equals("")){
			try {
				URL url = new URL(user.getProfilePicture());
				BufferedImage image = ImageIO.read(url);
				if(image==null){
					result.addError(new ObjectError("user", "The URL doesn't correspond to a valid image"));
				}
			} catch (IOException e) {
				result.addError(new ObjectError("user", "Error al leer la URL de la imagen"));
			}
		}

		if (result.hasErrors()) {
			return VIEWS_USER_CREATE_FORM;
		}
		else {
			//creating user, user, and authority
			this.userService.saveUser(user);
			return "redirect:/";
		}
	}


	@GetMapping(value = { "/users" })
	public String showUserList(HttpServletRequest req, Map<String, Object> model, @PageableDefault(page = 0, size = 5)Pageable pageable) {
		Principal player = req.getUserPrincipal();
        String name = player.getName();
        User user = userService.findUser(name).get();
		List<User> admins = userService.getAllAdmins();
		Boolean admin = admins.contains(user);
		String type = "user";
		this.paginatingUtil.prepareModelForPagination(model, pageable, type,null);
		model.put("admin",admin);
		return VIEWS_USER_LIST;
	}

	@GetMapping(value="users/profile/{userId}")
	public String showUserProfile(@PathVariable("userId") String userId, Map<String, Object> model){
		User user = this.userService.findUser(userId).get();
		model.put("user",user);
		List<Game> gamesOfPlayer = this.userService.getAllGameByUsername(user);
		if(gamesOfPlayer.size()==0){
		model.put("totalDurationPlayerGames", 0);
		model.put("averageDurationPlayerGames", 0);

		return VIEWS_USER_PROFILE;
		}
		int averageDurationPlayerGames;
		int totalDurationPlayerGames;
		Duration totalDuration = Duration.ZERO;
		for (Game game : gamesOfPlayer) {
    		LocalDateTime start = game.getStart();
    		LocalDateTime end = game.getEnd();
    		Duration difference = Duration.between(start, end);
    		totalDuration = totalDuration.plus(difference);
		}
		totalDurationPlayerGames = (int)totalDuration.toSeconds();
		averageDurationPlayerGames = totalDurationPlayerGames / (int) gamesOfPlayer.size();
		model.put("totalDurationPlayerGames", totalDurationPlayerGames);
		model.put("averageDurationPlayerGames", averageDurationPlayerGames);

		return VIEWS_USER_PROFILE;
	}


	@GetMapping(value = "/users/update/{userId}")
	public String initUpdateUserForm(@PathVariable("userId") String userId, Model model) {
		User user = this.userService.findUser(userId).get();
		model.addAttribute(user);
		
		return VIEWS_USER_CREATE_FORM;
	}

	@PostMapping(value = "/users/update/{userId}")
	public String processUpdatePlayerForm(@Valid User user, BindingResult result,
			@PathVariable("userId") String userId) {
		if (result.hasErrors()) {
			return VIEWS_USER_CREATE_FORM;
		} else {
			user.setId(userId);
			user.setEnabled(true);

			this.userService.saveUser(user);

			return "redirect:/users";
		}
	}

	@GetMapping(value = "users/delete/{userId}")
	public String deleteUser(@PathVariable("userId") String userId) {
		List<Game> game = gameService.getAllGameByUsername(userId);
		List<Audit> audit = auditService.getAllAuditByUsername(userId);
		gameService.deleteAllGames(game);
		auditService.deleteAllAudit(audit);
		userService.deleteUser(userId);
		return "redirect:/users";

	}


}
