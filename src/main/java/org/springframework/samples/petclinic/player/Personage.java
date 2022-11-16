package org.springframework.samples.petclinic.player;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.core.style.ToStringCreator;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "personage")
public class Personage{

	private Integer id;

	private String name;

	private String url;

	@Override
	public String toString() {
		return new ToStringCreator(this)

				.append("id", this.getId())
				.append("name", this.getName())
				.append("url", this.getUrl()).toString();
	}

}

