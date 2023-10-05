package org.springframework.samples.minesweeper.saga;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface SagaRepository extends  CrudRepository<Saga, String>{

    @Query("SELECT saga FROM Saga saga WHERE saga.id = :sagaId")
    Saga findSagaById(@Param("sagaId") Integer sagaId);

    @Query("SELECT s FROM Saga s ORDER BY s.id DESC")
    List<Saga> getAllSagasOrdered(Pageable p);
    
}
