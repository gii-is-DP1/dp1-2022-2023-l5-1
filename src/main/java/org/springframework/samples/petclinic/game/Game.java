package org.springframework.samples.petclinic.game;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.samples.petclinic.deck.Deck;
import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.player.Player;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "games")
@Getter
@Setter
public class Game extends BaseEntity{
   /*
    private Player host;
   
    
    private Deck deck; */

}
