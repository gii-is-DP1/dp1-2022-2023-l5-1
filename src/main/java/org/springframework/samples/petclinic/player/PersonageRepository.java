package org.springframework.samples.petclinic.player;

import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.samples.petclinic.model.BaseEntity;

public interface PersonageRepository extends Repository<Personage, Integer> {
    
	/**
	 * Save an <code>Personage</code> to the data store, either inserting or updating it.
	 * @param personage the <code>Personage</code> to save
	 * @see BaseEntity#isNew
	 */
	void save(Personage personage) throws DataAccessException;

	/**
	 * Retrieve <code>Personage</code>s from the data store by name, returning all personage
	 * whose name <i>starts</i> with the given name.
	 * @param name Value to search for
	 * @return a <code>Collection</code> of matching <code>Personage</code>s (or an empty
	 * <code>Collection</code> if none found)
	 */	
	@Query("SELECT DISTINCT personage FROM Personage personage left join fetch personage.pets WHERE player.name LIKE :name%")
	public Collection<Personage> findByName(@Param("name") String lastName);


	/**
	 * Retrieve an <code>Personage</code> from the data store by id.
	 * @param id the id to search for
	 * @return the <code>Personage</code> if found
	 * @throws org.springframework.dao.DataRetrievalFailureException if not found
	 */	
	@Query("SELECT personage FROM Personage personage left join fetch personage.pets WHERE personage.id =:id")
	public Personage findById(@Param("id") int id);

}
