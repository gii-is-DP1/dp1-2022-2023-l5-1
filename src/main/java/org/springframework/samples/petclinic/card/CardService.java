package org.springframework.samples.petclinic.card;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javassist.NotFoundException;

@Service
public class CardService {

    @Autowired
    private static CardRepository cardRepository;


    @Transactional // (readOnly = true)
    public static Iterable<Card> findAll() {
        return cardRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Collection<CardType> findCardTypes() throws DataAccessException {
        Collection<CardType> ct = new ArrayList<>();
        ct.add(CardType.WEAPON);
        ct.add(CardType.ABILITY);
        ct.add(CardType.UTILITY);
        return ct;
    }

    @Transactional
    public int cardCount() {
        return (int) cardRepository.count();
    }

    @Transactional
    public void saveCard(Card card) {
        // creating card
        cardRepository.save(card);
    }

    @Transactional
    public Card findCardById(int id) throws DataAccessException, NotFoundException {
        Optional<Card> card = cardRepository.findById(id);
        if(!card.isEmpty()){
            return card.get();
        }else{
            throw new NotFoundException("Card {id:"+id+"} no encontrada");
        }  
    }

    /**
     * Initialize a new deck in a game
     * 
     * @return Deck randomized
     */
    // @Transactional
    // public void newDeck(Game game) {
    //     Deck deck = new Deck(findAll());
    //     mapGameRepository.putDeck(game, deck);
    // }

    // /**
    //  * Next card in the game's deck
    //  * 
    //  * @param game linked to the deck
    //  * @return Card from the deck if there are left
    //  */
    // @Transactional
    // public Card nextCard(Game game) {
    //     Deck deck = mapGameRepository.getDeck(game);
    //     Card card = deck.stealCard();
    //     mapGameRepository.putDeck(game, deck);
    //     return card;
    // }

}