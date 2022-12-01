package org.springframework.samples.petclinic.hand;

import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.player.Player;
import org.springframework.samples.petclinic.player.Rol;

import java.util.List;

import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;

import org.springframework.samples.petclinic.card.Card;

public class Hand extends BaseEntity{

    @NotBlank
    @OneToOne
	private Player player;

    @ManyToOne
    private List<Card> cards;

	@NotBlank
	private Rol rol;

    
}
