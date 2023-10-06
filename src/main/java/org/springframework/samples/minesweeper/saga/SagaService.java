package org.springframework.samples.minesweeper.saga;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Mostly used as a facade for all Minesweeper controllers Also a placeholder
 * for @Transactional and @Cacheable annotations
 *
 * @author Michael Isvy
 */
@Service
public class SagaService {

	private SagaRepository sagaRepository;

	@Autowired
	public SagaService(SagaRepository sagaRepository) {
		this.sagaRepository = sagaRepository;
	}

	@Transactional
	public void saveSaga(Saga saga) throws DataAccessException {
		sagaRepository.save(saga);
	}
	
	public Saga findSaga(String saga) {
		return sagaRepository.findSagaById(Integer.valueOf(saga));
	}

	@Transactional(readOnly = true)	
	public Collection<Saga> findSagas() throws DataAccessException {
		return (Collection<Saga>) sagaRepository.findAll();
	}

    public List<Saga> getAllSagasOrdered(Integer page, Pageable p) {
        return sagaRepository.getAllSagasOrdered(p);
    }
    	
}