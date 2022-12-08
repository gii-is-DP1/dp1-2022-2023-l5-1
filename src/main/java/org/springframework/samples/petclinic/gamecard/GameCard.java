package org.springframework.samples.petclinic.gamecard;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.samples.petclinic.card.Card;
import org.springframework.samples.petclinic.game.Game;
import org.springframework.samples.petclinic.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "games_cards", uniqueConstraints = @UniqueConstraint(name = "uniqueGameCard", columnNames = { "game_id",
      "card_id" }))
public class GameCard extends BaseEntity {

   @ManyToOne(optional = false)
   @JoinColumn(name = "game_id")
   private Game game;

   @ManyToOne(optional = false)
   @JoinColumn(name = "card_id")
   private Card card;

   private Boolean sold;

}