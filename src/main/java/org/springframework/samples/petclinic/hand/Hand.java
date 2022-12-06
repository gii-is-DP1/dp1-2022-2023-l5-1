package org.springframework.samples.petclinic.hand;

import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.player.Player;

import java.util.List;

import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.samples.petclinic.card.Card;

public class Hand extends BaseEntity{

    @NotBlank
    @OneToOne
	private Player player;

    @NotNull
    @OneToMany
    private List<Card> cards;


    
}
