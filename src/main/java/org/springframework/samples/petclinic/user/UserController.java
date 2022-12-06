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
package org.springframework.samples.petclinic.user;

import java.security.Principal;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.player.Player;
import org.springframework.samples.petclinic.player.PlayerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Juergen Hoeller
 * @author Ken Krebs
 * @author Arjen Poutsma
 * @author Michael Isvy
 */
@Controller
@RequestMapping("/users")
public class UserController {
	private static final String USER_NOT_FOUND = "User not found";
    private static final String USER = "user";
    private static final String ERROR_MESSAGE = "errorMessage";
    private static final String MESSAGE = "message";
    private static final String GAMES = "games";
    private static final String ERROR = "/error";
	private static final String VIEWS_OWNER_CREATE_FORM = "users/createOwnerForm";

	private final OwnerService ownerService;
    
    @Autowired
    private UserService userService;

	@Autowired
	public UserController(PlayerService clinicService) {
		this.playerService = clinicService;
	}
	//PERFIL
	@GetMapping("/profile")    
    public String profileFromMenu(Principal principal ,ModelMap modelMap){
        return String.format("redirect:/users/profile/%s",principal.getName());
    }


    @GetMapping("/profile/{username}")
    public String profile(@PathVariable("username") String username, ModelMap modelMap){
        String view = "users/usersProfile";
        
        Optional<User> user = userService.findUser(username);
        if(user.isPresent()){
            modelMap.put(USER, user);
            
        }else{
            modelMap.addAttribute(MESSAGE, USER_NOT_FOUND);
            view = ERROR; 
        }
        return view;
    }	
	@GetMapping("/profile/{username}/editProfile")
    public String viewEditProfile(@PathVariable("username") String username, ModelMap modelMap){
        String view = "users/usersEditProfileForm";
        
        Optional<User> user = userService.findUser(username);
        if(user.isPresent()){
            modelMap.put(USER, user);
            
        }else{
            modelMap.addAttribute(MESSAGE, USER_NOT_FOUND);
            view = ERROR; 
        }
        return view;
    }	
	@PostMapping(value = "/profile/{username}/editProfile")
	public String confirmEditProfile(@Valid User user, BindingResult result,@PathVariable("username") String username) {
		if (result.hasErrors()) {
			return "users/usersEditProfileForm";
		}
		else {
			user.setUsername(username);
			this.userService.saveUser(user);
			return "welcome";
		}
	}


	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	
	@GetMapping(value = "/users/new")
	public String initCreationForm(Map<String, Object> model) {
		Player player = new Player();
		model.put("player", player);
		return VIEWS_OWNER_CREATE_FORM;
	}

	@PostMapping(value = "/users/new")
	public String processCreationForm(@Valid Player player, BindingResult result) {
		if (result.hasErrors()) {
			return VIEWS_OWNER_CREATE_FORM;
		}
		else {
			//creating player, user, and authority
			this.playerService.savePlayer(player);
			return "redirect:/";
		}
	}

}
