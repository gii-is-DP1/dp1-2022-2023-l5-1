package org.springframework.samples.petclinic.playercard;

import org.springframework.stereotype.Repository;
import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * 
 * @author Jose Maria Delgado Sanchez
 */
@Repository
public interface PlayerCardRepository extends CrudRepository<PlayerCard, Integer> {
    
    /**
     * Retrieve <code>PlayerCard</code>s from the data store.
     * 
     * @return a <code>PlayerCard</code>
     */
    @Query("SELECT pc FROM PlayerCard pc WHERE pc.player.id = ?1 AND pc.card.id = ?2")
    PlayerCard findByPlayerCard(int playerId, int cardId) throws DataAccessException;
}
