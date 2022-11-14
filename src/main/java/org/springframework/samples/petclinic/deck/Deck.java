package org.springframework.samples.petclinic.deck;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.springframework.samples.petclinic.card.Card;
import org.springframework.samples.petclinic.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "deck")
public class Deck extends BaseEntity {
    //Por si hacemos diferentes mazos (3 jugadores¿?)
    @Column(name="name")
    private String name;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    
	private List<Card> cards;

    public void deleteCards(Card card){
        cards.remove(card);
    }
    
}