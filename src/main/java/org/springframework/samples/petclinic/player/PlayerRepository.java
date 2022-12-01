package org.springframework.samples.petclinic.player;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends CrudRepository<Player, String> {
    
	@Query(value = "SELECT * FROM Players where id LIKE ?1", nativeQuery = true) 
	Optional<Player> findPlayerById(int id);
}
