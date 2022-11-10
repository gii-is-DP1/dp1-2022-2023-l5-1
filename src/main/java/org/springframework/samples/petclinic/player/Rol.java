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

import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "rol")
public class Rol{

    private Integer id;

	private String nombre;

	private Integer stars;

	@Override
	public String toString() {
		return new ToStringCreator(this)

				.append("id", this.getId())
				.append("name", this.getNombre())
                .append("stars", this.getStars()).toString();
	}

}