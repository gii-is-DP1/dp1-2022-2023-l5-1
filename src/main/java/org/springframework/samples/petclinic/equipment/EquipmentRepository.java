package org.springframework.samples.petclinic.equipment;

import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.CrudRepository;

public interface EquipmentRepository extends CrudRepository<Equipment, String> {
	
	@Query(value = "SELECT * FROM Equipment where id LIKE ?1", nativeQuery = true) 
	Optional<Equipment> findEquipmentById(int id);

	@Query("SELECT equipment FROM Equipment equipment left join fetch equipment.id WHERE equipment.id =:id")
	public Equipment findById(@Param("id") int id);
}
