package org.springframework.samples.petclinic.player;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.springframework.core.style.ToStringCreator;
import org.springframework.samples.petclinic.model.Person;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "character")
public class Personage extends Person {

	@Column(name = "name")
	@NotEmpty
	private String nombre;

	@Column(name = "image")
	@NotEmpty
	private String url;

	@Override
	public String toString() {
		return new ToStringCreator(this)

				.append("id", this.getId()).append("new", this.isNew())
				.append("lastName", this.getLastName())
				.append("name", this.getFirstName()).toString();
	}

}

