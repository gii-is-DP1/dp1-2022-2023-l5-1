package org.springframework.samples.petclinic.game;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Entity;


import org.springframework.samples.petclinic.model.NamedEntity;
import org.springframework.samples.petclinic.player.Player;
import org.springframework.samples.petclinic.user.User;

import lombok.Getter;
import lombok.Setter;



@Entity
@Getter
@Setter
public class Game extends NamedEntity{

   
   private User creator;

   private Integer turn;

   private String winner;

   private LocalDateTime startTime;

   private LocalDateTime endTime;

   private Integer maxNumberOfPlayers;

   private List<Player> players;

}