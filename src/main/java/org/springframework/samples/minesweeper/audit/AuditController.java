package org.springframework.samples.minesweeper.audit;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.samples.minesweeper.customComponents.PaginatingUtil;
import org.springframework.samples.minesweeper.genre.GenreService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class AuditController {
	
	private static final String VIEWS_ADMIN_AUDITS = "viewAudits";

	
	private  final AuditService auditService;
	private final PaginatingUtil paginatingUtil;

	@Autowired
	public AuditController(AuditService auditService, PaginatingUtil pu) {
		this.auditService = auditService;
		this.paginatingUtil = pu;
	}

	@GetMapping(value = "/audits")
	public String selectGame(Map<String, Object> model, HttpServletRequest request, @PageableDefault(page = 0, size = 5)@SortDefault.SortDefaults({
		@SortDefault(sort = "id", direction = Sort.Direction.ASC),
		@SortDefault(sort = "user.username", direction = Sort.Direction.DESC)})Pageable pageable) {
		Integer page=0;
		List<Audit> ls = auditService.findAudits(page,pageable);
		Integer totalElements = auditService.findAll().size();
		paginatingUtil.prepareModelForPagination(model, pageable, ls, totalElements);
		return VIEWS_ADMIN_AUDITS;
	}
}