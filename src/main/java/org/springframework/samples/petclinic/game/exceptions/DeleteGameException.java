package org.springframework.samples.petclinic.game.exceptions;

public class DeleteGameException extends Exception{
    public DeleteGameException(String errorMessage){
        super(errorMessage);
    }
}
