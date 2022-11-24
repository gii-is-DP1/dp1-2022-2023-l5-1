package org.springframework.samples.petclinic.player;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.core.style.ToStringCreator;
import org.springframework.samples.petclinic.user.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "players")
public class Player {

    private Integer id;

	private String nickname;

	private Integer endurance;

	private Integer honor;

	private Rol rol;

	private Personage personage;

	private User user;
	

	@Override
	public String toString() {
		return new ToStringCreator(this)
				.append("id", this.getId()).append("nickname", this.getNickname()).append("endurance", this.getEndurance())
				.append("honor", this.getHonor()).append("rol", this.getRol()).append("personage", this.getPersonage())
				.append("user", this.getUser()).toString();
	}

}