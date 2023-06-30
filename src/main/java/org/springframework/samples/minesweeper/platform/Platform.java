package org.springframework.samples.minesweeper.platform;


import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;


import org.springframework.samples.minesweeper.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="platforms")
public class Platform extends BaseEntity {
	
	@NotBlank
	String name;

}