package org.springframework.samples.petclinic.board;

import java.util.List;

import org.springframework.samples.petclinic.player.Player;


public class BoardBuilder implements BoardBuilderInterface{
    private List<Player> players;

    @Override
    public BoardBuilderInterface setPositions(List<Player> players){
        this.players=players;
        return this;
    }

    @Override
    public Board build(){
        return new Board(players);
    }
    
}

