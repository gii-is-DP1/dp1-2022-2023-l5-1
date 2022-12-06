package org.springframework.samples.petclinic.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends  CrudRepository<User, String>{
	@Query(value = "SELECT * FROM Players where id LIKE ?1", nativeQuery = true) 
	Optional<User> findPlayerById(int id);
}
