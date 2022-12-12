package org.springframework.samples.petclinic.game;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.samples.petclinic.player.Player;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends CrudRepository<Game,Integer>{
  
    Iterable<Game> findAll();
    
    @Query("SELECT P from Game P WHERE P.code = :code")
    Iterable<Game> findGamesByRoomCode(String code) throws DataAccessException;

    @Query("Select P from Player P where P.game= :id")
    List<Player> findPlayersByGameId(Integer id) throws DataAccessException;
}
