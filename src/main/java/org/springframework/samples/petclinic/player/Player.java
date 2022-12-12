package org.springframework.samples.petclinic.player;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.samples.petclinic.equipment.Equipment;
import org.springframework.samples.petclinic.game.Game;
import org.springframework.samples.petclinic.hand.Hand;
import org.springframework.samples.petclinic.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "players")
@Getter
@Setter
public class Player extends BaseEntity{
    
	@NotBlank
	private String nickname;

	@NotNull
	private Integer honor;

	@NotNull
	private Integer resistance;

	@NotBlank
	private String rol;

	@NotBlank
	@OneToOne
	private Hand hand;

	@NotBlank
	@OneToOne
	private Equipment equipment;
	
	@NotBlank
	@OneToOne
	private Game game;

}
