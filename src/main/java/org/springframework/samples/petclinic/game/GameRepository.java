package org.springframework.samples.petclinic.game;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface GameRepository extends CrudRepository<Game, Integer>{
   
    @Query("SELECT P from Game P WHERE P.code = :code")
    Iterable<Game> findGamesByRoomCode(String code) throws DataAccessException;
  
}