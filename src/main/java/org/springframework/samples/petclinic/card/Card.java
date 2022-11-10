package org.springframework.samples.petclinic.card;

import javax.persistence.Entity;
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

     public enum cardType{
        WEAPON,
        ABILITY,
        UTILITY;
    }
}
