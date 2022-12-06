package org.springframework.samples.petclinic.equipment;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EquipmentService {

	private EquipmentRepository equipmentRepository;	
	
	@Autowired
	public EquipmentService(EquipmentRepository equipmentRepository) {
		this.equipmentRepository = equipmentRepository;
	}	

	@Transactional
    public Optional<Equipment> findEquipmentById(int id){
        return equipmentRepository.findEquipmentById(id);
    }

	@Transactional
	public void saveEquipment(Equipment equipment) throws DataAccessException {
		//creating equipment
		equipmentRepository.save(equipment);
	}		

}
