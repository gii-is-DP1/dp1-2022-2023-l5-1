package org.springframework.samples.minesweeper.game;

import java.util.List;
import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface GameRepository extends  CrudRepository<Game, String>{

    @Query("SELECT game FROM Game game WHERE game.id = :gameId")
    Game findGameById(@Param("gameId") Integer gameId);

    @Query("SELECT game FROM Game game WHERE game.user.username = :username and game.inProgress = true")
    Game findActiveGameByUsername(@Param("username") String username);

    @Query("SELECT game FROM Game game WHERE game.user.username = :username")
    List<Game> findAllByUsername(@Param("username") String username);

    @Query("SELECT g FROM Game g WHERE g.inProgress=true")
    Collection<Game> findAllActiveGames();

    @Query("SELECT g FROM Game g WHERE g.inProgress=false")
    Collection<Game> findAllFinishGames();
    
}
