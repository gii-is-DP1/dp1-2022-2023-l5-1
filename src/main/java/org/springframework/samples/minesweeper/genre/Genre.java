package org.springframework.samples.minesweeper.genre;


import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;


import org.springframework.samples.minesweeper.model.BaseEntity;
import org.springframework.samples.minesweeper.model.NamedEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="genres")
public class Genre extends NamedEntity {

}