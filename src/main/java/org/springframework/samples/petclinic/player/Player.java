package org.springframework.samples.petclinic.player;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

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
	private Integer honnor;

	@NotNull
	private Integer resistance;

	@NotBlank
	private String rol;

	@NotBlank
	private String heroImg;

}
