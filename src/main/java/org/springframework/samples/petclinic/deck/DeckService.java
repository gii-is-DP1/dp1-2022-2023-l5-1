package org.springframework.samples.petclinic.deck;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.card.Card;
import org.springframework.samples.petclinic.card.CardService;
import org.springframework.stereotype.Service;

@Service
public class DeckService {

    @Autowired
    private DeckRepository deckRepo;

    @Transactional
    public Iterable<Deck> findByCardId(int id) {
        return deckRepo.findByCardId(id);
    }

    @Transactional
    public Iterable<Integer> getCardsOnDeck(int deckId) {  
        return deckRepo.findCardsInDeck(deckId);
    }


    @Transactional
    public Deck getDeck(int id) {  
        return deckRepo.findDeckById(id);
    }

    @Transactional
    public Deck init(String name) {  
        Deck deck = new Deck();
        deck.setName(name);
        Iterable<Card> cardsI = CardService.findAll();
        List<Card> cards = StreamSupport.stream(cardsI.spliterator(), false).collect(Collectors.toList());
        Collections.shuffle(cards);
        deck.setCards(cards);
        deckRepo.save(deck);
        return deck;
    }

    @Transactional
    public void save(Deck deck) {  
        deckRepo.save(deck);
    }

}
