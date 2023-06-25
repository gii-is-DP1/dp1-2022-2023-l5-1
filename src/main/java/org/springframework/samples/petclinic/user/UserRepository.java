package org.springframework.samples.petclinic.user;

import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends  CrudRepository<User, String>{
	Collection<User> findAll() throws DataAccessException;
	
    @Query("SELECT user FROM User user WHERE user.username LIKE :username")
    public User findByUsername(@Param("username")String username);
}
