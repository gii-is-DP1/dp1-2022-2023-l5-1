package org.springframework.samples.petclinic.equipment;

import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.player.Player;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@Entity
public class Equipment extends BaseEntity{

    @NotBlank
    @OneToOne
	private Player player;

    @NotEmpty
    private Integer armor;

    @NotEmpty
    private Integer fastDraw;

    @NotEmpty
    private Integer focus;

    
}
