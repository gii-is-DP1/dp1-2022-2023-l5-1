package org.springframework.samples.petclinic.hand;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class HandService {

	private HandRepository equipmentRepository;	
	
	@Autowired
	public HandService(HandRepository equipmentRepository) {
		this.equipmentRepository = equipmentRepository;
	}	

	@Transactional
    public Optional<Hand> findHandById(int id){
        return equipmentRepository.findHandById(id);
    }

	@Transactional
	public void saveHand(Hand equipment) throws DataAccessException {
		//creating equipment
		equipmentRepository.save(equipment);
	}		

}
