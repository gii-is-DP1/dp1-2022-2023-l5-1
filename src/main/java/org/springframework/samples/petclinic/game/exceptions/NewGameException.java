package org.springframework.samples.petclinic.game.exceptions;

public class NewGameException extends Exception {
    public NewGameException(String errorMessage){
        super(errorMessage);
    }
}
