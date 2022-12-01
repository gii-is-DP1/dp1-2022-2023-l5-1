
package org.springframework.samples.petclinic.player;

import java.util.Optional;
import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.data.repository.CrudRepository;

public interface PlayerRepository extends CrudRepository<Player, String> {
    
	@Query(value = "SELECT * FROM Players where id LIKE ?1", nativeQuery = true) 
	Optional<Player> findPlayerById(int id);

	/**
	 * Retrieve <code>Player</code>s from the data store by name, returning all player
	 * whose name <i>starts</i> with the given name.
	 * @param name Value to search for
	 * @return a <code>Collection</code> of matching <code>Player</code>s (or an empty
	 * <code>Collection</code> if none found)
	 */	
	@Query("SELECT DISTINCT player FROM Player player left join fetch player.nickname WHERE player.nickname LIKE :nickname%")
	public Collection<Player> findByNickname(@Param("nickname") String nickName);


	/**
	 * Retrieve an <code>Player</code> from the data store by id.
	 * @param id the id to search for
	 * @return the <code>Player</code> if found
	 * @throws org.springframework.dao.DataRetrievalFailureException if not found
	 */	
	@Query("SELECT player FROM Player player left join fetch player.id WHERE player.id =:id")
	public Player findById(@Param("id") int id);

}
