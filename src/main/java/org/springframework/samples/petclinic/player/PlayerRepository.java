
package org.springframework.samples.petclinic.player;

import java.util.Optional;
import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.CrudRepository;

public interface PlayerRepository extends CrudRepository<Player, String> {
    
	@Query(value = "SELECT * FROM Players where id LIKE ?1", nativeQuery = true) 
	Optional<Player> findPlayerById(int id);

	@Query("SELECT DISTINCT player FROM Player player left join fetch player.nickname WHERE player.nickname LIKE :nickname%")
	public Collection<Player> findByNickname(@Param("nickname") String nickName);
	
	@Query("SELECT player FROM Player player left join fetch player.id WHERE player.id =:id")
	public Player findById(@Param("id") int id);

}
