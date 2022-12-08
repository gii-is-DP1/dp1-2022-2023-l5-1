package org.springframework.samples.petclinic.hand;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.CrudRepository;

public interface HandRepository extends CrudRepository<Hand, String> {
	
	@Query(value = "SELECT * FROM Hand where id LIKE ?1", nativeQuery = true) 
	Optional<Hand> findHandById(int id);
}
