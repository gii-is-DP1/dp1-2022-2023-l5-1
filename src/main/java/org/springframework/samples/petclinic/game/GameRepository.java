package org.springframework.samples.petclinic.game;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public class GameRepository extends CrudRepository<Game, Integer> {

	@Query("SELECT game FROM Game game WHERE game.turn IS 0")
	List<Game> findLobbies() throws DataAccessException;   

    @Query("SELECT game FROM Game game WHERE game.winner IS NULL AND turn > 0")
	List<Game> findOnGoingGames() throws DataAccessException;
    
}
