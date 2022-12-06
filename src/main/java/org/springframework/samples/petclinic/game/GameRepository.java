package org.springframework.samples.petclinic.game;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends CrudRepository<Game,Integer>{
  
    Iterable<Game> findAll();
    
    @Query("SELECT P from Game P WHERE P.code = :code")
    Iterable<Game> findGamesByRoomCode(String code) throws DataAccessException;
}
