package org.springframework.samples.petclinic.card;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


public class Deck {

    private List<Card> cards;

    /**
     * Constructor given a list of cards initiate a deck object
     */
    //TODO: ajustar la inicializacion del mazo a nuestro juego
    public Deck(Iterable<Card> allCards) {
        List<Card> cards = StreamSupport.stream(allCards.spliterator(), false).collect(Collectors.toList());

        Collections.shuffle(cards);
        this.cards = cards;
    }

    /**
     * If there are cards left in the deck, it returns the next card
     * 
     * @return The next card in the deck
     */
    public Card stealCard() {
        if (!this.cards.isEmpty()) {
            Card card = this.cards.remove(0);
            return card;
        } else {
            return null;
        }
    }

    /**
     * Discard a list of cards (This is used for data initialization purposes)
     */
    public void discard(Integer[] cardIds){
        for(Integer id: cardIds){
            Optional<Card> card = cards.stream()
                                .filter(c -> c.getId().equals(id))
                                .findAny();

            if(card.isPresent()){
                cards.remove(card.get());
            }  
        }
    }


    /**
     * Check if the deck is empty
     * 
     * @return true if there are no cards left, false if not
     */
    public Boolean isEmpty() {
        return this.cards.isEmpty();
    }
    
}
