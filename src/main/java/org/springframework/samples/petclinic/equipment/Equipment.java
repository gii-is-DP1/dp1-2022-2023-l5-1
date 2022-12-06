package org.springframework.samples.petclinic.equipment;

import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.player.Player;

import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;


public class Equipment extends BaseEntity{

    @NotBlank
    @OneToOne
	private Player player;

    @NotEmpty
    private Integer armor;

    @NotEmpty
    private Integer FastDraw;

    @NotEmpty
    private Integer Focus;

    
}
