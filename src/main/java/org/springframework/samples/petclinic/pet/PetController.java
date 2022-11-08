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
package org.springframework.samples.petclinic.pet;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.pet.exceptions.DuplicatedPetNameException;
import org.springframework.samples.petclinic.player.Player;
import org.springframework.samples.petclinic.player.PlayerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Juergen Hoeller
 * @author Ken Krebs
 * @author Arjen Poutsma
 */
@Controller
@RequestMapping("/players/{playerId}")
public class PetController {

	private static final String VIEWS_PETS_CREATE_OR_UPDATE_FORM = "pets/createOrUpdatePetForm";

	private final PetService petService;
        private final PlayerService playerService;

	@Autowired
	public PetController(PetService petService, PlayerService playerService) {
		this.petService = petService;
                this.playerService = playerService;
	}

	@ModelAttribute("types")
	public Collection<PetType> populatePetTypes() {
		return this.petService.findPetTypes();
	}

	@ModelAttribute("player")
	public Player findPlayer(@PathVariable("playerId") int playerId) {
		return this.playerService.findPlayerById(playerId);
	}
        
        /*@ModelAttribute("pet")
	public Pet findPet(@PathVariable("petId") Integer petId) {
            Pet result=null;
		if(petId!=null)
                    result=this.clinicService.findPetById(petId);
                else
                    result=new Pet();
            return result;
	}*/
                
	@InitBinder("player")
	public void initPlayerBinder(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}

	@InitBinder("pet")
	public void initPetBinder(WebDataBinder dataBinder) {
		dataBinder.setValidator(new PetValidator());
	}

	@GetMapping(value = "/pets/new")
	public String initCreationForm(Player player, ModelMap model) {
		Pet pet = new Pet();
		player.addPet(pet);
		model.put("pet", pet);
		return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping(value = "/pets/new")
	public String processCreationForm(Player player, @Valid Pet pet, BindingResult result, ModelMap model) {		
		if (result.hasErrors()) {
			model.put("pet", pet);
			return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
		}
		else {
                    try{
                    	player.addPet(pet);
                    	this.petService.savePet(pet);
                    }catch(DuplicatedPetNameException ex){
                        result.rejectValue("name", "duplicate", "already exists");
                        return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
                    }
                    return "redirect:/players/{playerId}";
		}
	}

	@GetMapping(value = "/pets/{petId}/edit")
	public String initUpdateForm(@PathVariable("petId") int petId, ModelMap model) {
		Pet pet = this.petService.findPetById(petId);
		model.put("pet", pet);
		return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
	}

    /**
     *
     * @param pet
     * @param result
     * @param petId
     * @param model
     * @param player
     * @param model
     * @return
     */
        @PostMapping(value = "/pets/{petId}/edit")
	public String processUpdateForm(@Valid Pet pet, BindingResult result, Player player,@PathVariable("petId") int petId, ModelMap model) {
		if (result.hasErrors()) {
			model.put("pet", pet);
			return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
		}
		else {
                        Pet petToUpdate=this.petService.findPetById(petId);
			BeanUtils.copyProperties(pet, petToUpdate, "id","player","visits");                                                                                  
                    try {                    
                        this.petService.savePet(petToUpdate);                    
                    } catch (DuplicatedPetNameException ex) {
                        result.rejectValue("name", "duplicate", "already exists");
                        return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
                    }
			return "redirect:/players/{playerId}";
		}
	}

}
