package org.springframework.samples.petclinic.gamecard;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameCardRepository extends CrudRepository<GameCard, Integer> {

	/**
	 * Retrieve <code>GameCard</code>s from the data store.
	 * 
	 * @return a <code>GameCard</code> of <code>Game</code>s
	 */
	@Query("SELECT gc FROM GameCard gc WHERE gc.game.id = ?1 AND gc.card.id = ?2")
	GameCard findByGameCard(int gameId, int cardId) throws DataAccessException;
}
