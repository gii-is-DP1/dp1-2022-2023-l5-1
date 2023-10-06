package org.springframework.samples.minesweeper.saga;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.samples.minesweeper.customComponents.PaginatingUtil;
import org.springframework.samples.minesweeper.platform.Platform;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SagaController {

  	private static final String VIEWS_SAGA_LIST = "sagas/listSaga";
	private static final String VIEWS_SAGA_UPDATE_FORM = "sagas/updateSagaForm";

	private final SagaService sagaService;
	private final PaginatingUtil paginatingUtil;

	@Autowired
	public SagaController(SagaService clinicService, PaginatingUtil pu) {
		this.sagaService = clinicService;
		this.paginatingUtil = pu;
	}
    
    @GetMapping(value = { "/sagas" })
	public String showSagaList(Map<String, Object> model, @PageableDefault(page = 0, size = 5)@SortDefault.SortDefaults({
		@SortDefault(sort = "id", direction = Sort.Direction.ASC),
		@SortDefault(sort = "name", direction = Sort.Direction.DESC)})Pageable pageable) {
		
		String type = "sagas";
		this.paginatingUtil.prepareModelForPagination(model, pageable, type,null);
		return VIEWS_SAGA_LIST;
	}

    @GetMapping(value = "/sagas/update")
	public String initUpdateForm(Map<String, Object> model, @RequestParam() String sagaId) {
        Saga saga = this.sagaService.findSaga(sagaId);
		model.put("saga", saga);
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
			return "redirect:/sagas";
		}
	}
}
