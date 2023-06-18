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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.user.User;
import org.springframework.samples.petclinic.user.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;



@Controller
public class UserController {

	private static final String VIEWS_OWNER_CREATE_FORM = "users/createUserForm";
  private static final String VIEWS_OWNER_CREATE_FORM = "users/createOwnerForm";

	private final UserService userService;
	private final AuthoritiesService authoService;

	@Autowired
	public UserController(UserService clinicService,AuthoritiesService clinicService2) {
		this.userService = clinicService;
		this.authoService = clinicService2;
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	@GetMapping(value = "/users/new")
	public String initCreationForm(Map<String, Object> model) {
		User user = new User();
		model.put("user", user);
		return VIEWS_OWNER_CREATE_FORM;
	}

	@PostMapping(value = "/users/new")
	public String processCreationForm(@Valid User user, BindingResult result) {
		if (result.hasErrors()) {
			return VIEWS_OWNER_CREATE_FORM;
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
		return VIEWS_OWNER_CREATE_FORM;
	}
	@PostMapping(value = "/users/update")
	public String processUpdateForm(@Valid User user, BindingResult result) {
		if (result.hasErrors()) {
			return VIEWS_OWNER_CREATE_FORM;
		}
		else {
			//creating user, user, and authority
			this.userService.saveUser(user);
			return "redirect:/";
		}
	}


	@GetMapping(value = { "/users" })
	public String showVetList(Map<String, Object> model) {
		// Here we are returning an object of type 'Vets' rather than a collection of Vet
		// objects
		// so it is simpler for Object-Xml mapping
		List<User> users = new ArrayList<>(this.userService.findUsers());
		model.put("users", users);
		return VIEWS_OWNER_LIST;
	}

}
