package org.springframework.samples.petclinic.card;


import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table (name="weapons")
public class Weapon extends Card{

    @NotNull
    public Integer damage;

    @NotNull
    public Integer range;
    
}
