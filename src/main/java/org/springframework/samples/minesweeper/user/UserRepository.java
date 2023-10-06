package org.springframework.samples.minesweeper.user;

import java.util.Collection;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.minesweeper.game.Game;

public interface UserRepository extends  CrudRepository<User, String>{
	Collection<User> findAll() throws DataAccessException;
	
    @Query("SELECT user FROM User user WHERE user.username LIKE :username")
    public User findByUsername(@Param("username")String username);

    @Query("SELECT DISTINCT authorities.user FROM Authorities authorities WHERE authorities.authority = 'player'")
    List<User> findAllPlayers();

    @Query("SELECT DISTINCT authorities.user FROM Authorities authorities WHERE authorities.authority = 'admin'")
    List<User> findAllAdmins();

    @Query("SELECT game FROM Game game WHERE game.user LIKE :user")
    List<Game> findAllGamesByPlayer(@Param("user")User user);

    @Query("SELECT u FROM User u JOIN u.authorities a WHERE a.authority = 'player' ORDER BY u.username DESC")
    List<User> findAllPlayersSorted(Pageable pageable);
}
