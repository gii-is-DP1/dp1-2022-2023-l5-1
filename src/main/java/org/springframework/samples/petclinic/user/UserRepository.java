package org.springframework.samples.petclinic.user;

import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends  CrudRepository<User, String>{
	Collection<User> findAll() throws DataAccessException;
}
