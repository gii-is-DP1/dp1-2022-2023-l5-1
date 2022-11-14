package org.springframework.samples.petclinic.board;

import java.util.List;

import org.springframework.samples.petclinic.player.Player;

public interface BoardBuilderInterface {
    BoardBuilderInterface setPositions(List<Player> players);
    Board build();
    
}

