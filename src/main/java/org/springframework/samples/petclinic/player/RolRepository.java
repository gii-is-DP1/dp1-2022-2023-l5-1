package org.springframework.samples.petclinic.player;

import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.BaseEntity;

public interface RolRepository extends Repository<Rol, Integer> {
    
	/**
	 * Save an <code>Rol</code> to the data store, either inserting or updating it.
	 * @param rol the <code>Rol</code> to save
	 * @see BaseEntity#isNew
	 */
	void save(Rol rol) throws DataAccessException;

	/**
	 * Retrieve <code>Rol</code>s from the data store by name, returning all rol
	 * whose name <i>starts</i> with the given name.
	 * @param name Value to search for
	 * @return a <code>Collection</code> of matching <code>Rol</code>s (or an empty
	 * <code>Collection</code> if none found)
	 */	
	@Query("SELECT DISTINCT rol FROM Rol rol left join fetch rol.name WHERE rol.name LIKE :name%")
	public Collection<Rol> findByName(@Param("name") String lastName);


	/**
	 * Retrieve an <code>Rol</code> from the data store by id.
	 * @param id the id to search for
	 * @return the <code>Rol</code> if found
	 * @throws org.springframework.dao.DataRetrievalFailureException if not found
	 */	
	@Query("SELECT rol FROM Rol rol left join fetch rol.id WHERE rol.id =:id")
	public Rol findById(@Param("id") int id);

}
