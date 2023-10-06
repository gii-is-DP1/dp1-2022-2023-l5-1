package org.springframework.samples.minesweeper.saga;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class SagaController {

  	private static final String VIEWS_SAGA_LIST = "sagas/listSaga";
	private static final String VIEWS_SAGA_UPDATE_FORM = "sagas/updateSagaForm";

	private final SagaService sagaService;

	@Autowired
	public SagaController(SagaService clinicService) {
		this.sagaService = clinicService;
	}
    
    @GetMapping(value = { "/sagas" })
	public String showSagaList(Map<String, Object> model) {
		
		List<Saga> sagas = new ArrayList<>(this.sagaService.findSagas());

		model.put("sagas", sagas);
		return VIEWS_SAGA_LIST;
	}

    @GetMapping(value = "/sagas/update")
	public String initUpdateForm(Map<String, Object> model, @RequestParam() String sagaId) {
        Saga saga = this.sagaService.findSaga(sagaId);
		model.put("saga", saga);
		log.info("Actualizando la saga {}",saga.getName());
		return VIEWS_SAGA_UPDATE_FORM;
	}
	@PostMapping(value = "/sagas/update")
	public String processUpdateForm(@Valid Saga saga, BindingResult result, @RequestParam String sagaId) {
		if (result.hasErrors()) {
			return VIEWS_SAGA_UPDATE_FORM;
		}
		else {
            Integer id = Integer.valueOf(sagaId);
            saga.setId(id);
			this.sagaService.saveSaga(saga);
			log.info("Saga actualizada a {}",saga.getName());
			return "redirect:/sagas";
		}
	}
}
