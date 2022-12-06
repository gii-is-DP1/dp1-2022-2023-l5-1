package org.springframework.samples.petclinic.card;


import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.springframework.samples.petclinic.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="cards")
@Getter
@Setter
public class Card extends BaseEntity{
     @NotEmpty
     public String name;

     @NotEmpty
     public String description;

     @NotEmpty
     public String cardImage;

     @Enumerated (value=EnumType.STRING)
     public CardType type;

     @ManyToOne(optional = true)
     @JoinColumn(name="weaponid")
     public Weapon weapon;

}