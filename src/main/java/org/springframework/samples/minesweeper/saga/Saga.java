package org.springframework.samples.minesweeper.saga;


import javax.persistence.Entity;
import javax.persistence.Table;
import org.springframework.samples.minesweeper.model.NamedEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="sagas")
public class Saga extends NamedEntity {

	
}