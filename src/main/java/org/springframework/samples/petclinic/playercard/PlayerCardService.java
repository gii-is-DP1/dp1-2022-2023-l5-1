package org.springframework.samples.petclinic.playercard;



import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.petclinic.card.Card;
import org.springframework.samples.petclinic.player.Player;
import org.springframework.stereotype.Service;



/**
 * @author Jose Maria Delgado Sanchez
 * @author Ricardo Nadal Garcia
 */
@Service
public class PlayerCardService {

    @Autowired
    private PlayerCardRepository playerCardRepository;

    @Transactional
    public void savePlayerCard(PlayerCard playerCard) throws DataAccessException {
        playerCardRepository.save(playerCard);
    }

    @Transactional
    public PlayerCard findByPlayerCard(Player player, Card card) throws DataAccessException{
        return playerCardRepository.findByPlayerCard(player.getId(), card.getId());
    }
   
}
