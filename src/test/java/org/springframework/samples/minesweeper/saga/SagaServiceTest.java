package org.springframework.samples.minesweeper.saga;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class SagaServiceTest {
	@Autowired
	private SagaService sagaService;

	
	@Test
	@Transactional
	void saveSagaTest() {

		List<Saga>sagas =  new ArrayList<>(this.sagaService.findSagas());
		Saga saga = sagas.get(0);

		String name1=saga.getName();
		saga.setName("Battlefield:2");
		sagaService.saveSaga(saga);
	
		assertThat(saga.getName()).isNotEqualTo(name1);  
	}

	
	@Test
	void findSagaByIdTest() {
		List<Saga>sagas =  new ArrayList<>(this.sagaService.findSagas());
	
		Saga saga = sagas.get(0);
		String id=saga.getId().toString();

		Saga saga2 = sagaService.findSaga(id);
	
		assertThat(saga).isEqualTo(saga2);  
	}
	
	@Test
	void findSagasTest() {
		List<Saga>sagas =  new ArrayList<>(this.sagaService.findSagas());
		Integer size = sagas.size();
		Integer expectedSize=sagaService.findSagas().size();	
		assertThat(size).isEqualTo(expectedSize);
	}
	
}