package org.springframework.samples.petclinic.gamecard;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.card.Card;
import org.springframework.samples.petclinic.card.CardService;
import org.springframework.samples.petclinic.card.Deck;
import org.springframework.samples.petclinic.game.Game;
import org.springframework.samples.petclinic.game.MapGameRepository;
import org.springframework.stereotype.Service;

/**
 * @author Jose Maria Delgado Sanchez
 */
@Service
public class GameCardService {

    @Autowired
    private GameCardRepository gameCardRepository;

    @Autowired
    private CardService cardService;

    @Autowired
    private MapGameRepository mapGameRepository;

    @Transactional
    public Iterable<GameCard> findAll() {
        return gameCardRepository.findAll();
    }

    @Transactional
    public void saveGameCard(GameCard gameCard) throws DataAccessException {
        gameCardRepository.save(gameCard);
    }   

}
