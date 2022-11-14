package org.springframework.samples.petclinic.game;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/games")
public class GameController {


    @GetMapping(path = "/new")
    public String createGame(ModelMap modelMap) { 
      
        return null;
    }

    @GetMapping(path = "/{code}/lobby")
    public String lobby(ModelMap modelMap){
       
        return null;
    }
    

}