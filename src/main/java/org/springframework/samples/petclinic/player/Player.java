package org.springframework.samples.petclinic.player;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.core.style.ToStringCreator;
import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.user.User;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "players")
public class Player extends BaseEntity{

	@NotBlank
	private String nickname;
	@NotNull
	private Integer resistance;
	@NotNull
	private Integer honor;
	@NotBlank
	private Rol rol;

	
	private User user;
	

	@Override
	public String toString() {
		return new ToStringCreator(this)
				.append("id", this.getId()).append("nickname", this.getNickname()).append("endurance", this.getResistance())
				.append("honor", this.getHonor()).append("rol", this.getRol()).append("user", this.getUser()).toString();
	}

}
