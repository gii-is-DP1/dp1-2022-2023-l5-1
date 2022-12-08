package org.springframework.samples.petclinic.playercard;


import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.samples.petclinic.card.Card;
import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.player.Player;

import lombok.Getter;
import lombok.Setter;

/**
 * @author José Maria Delgado Sanchez
 */
@Getter
@Setter
@Entity
@Table(name = "players_cards", uniqueConstraints = @UniqueConstraint(name = "uniquePlayerCard", columnNames = {
        "player_id", "card_id" }))
public class PlayerCard extends BaseEntity {

    @ManyToOne(optional = false)
    @JoinColumn(name = "player_id")
    private Player player;

    @ManyToOne(optional = false)
    @JoinColumn(name = "card_id")
    private Card card;

    private Boolean discarded;

    /**
     * Default constructor for PlayerCard
     */
    public PlayerCard() {
    }

    /**
     * Constructor for PlayerCard, discarded false by default
     * 
     * @param player
     * @param card
     */
    public PlayerCard(Player player, Card card) {
        this.player = player;
        this.card = card;
        this.discarded = false;
    }

}
