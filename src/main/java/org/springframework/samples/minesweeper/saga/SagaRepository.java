package org.springframework.samples.minesweeper.saga;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface SagaRepository extends  CrudRepository<Saga, String>{

    @Query("SELECT saga FROM Saga saga WHERE saga.id = :sagaId")
    Saga findSagaById(@Param("sagaId") Integer sagaId);
    
}
