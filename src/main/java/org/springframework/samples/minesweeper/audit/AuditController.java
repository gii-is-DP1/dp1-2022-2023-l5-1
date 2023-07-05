package org.springframework.samples.minesweeper.audit;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuditController {
	
	private static final String VIEWS_ADMIN_AUDITS = "viewAudits";

	@Autowired
	private AuditService auditService;

	@GetMapping(value = "/audits")
	public String selectGame(Map<String, Object> model, HttpServletRequest request, @PageableDefault(page = 0, size = 5)@SortDefault.SortDefaults({
		@SortDefault(sort = "id", direction = Sort.Direction.ASC),
		@SortDefault(sort = "user.username", direction = Sort.Direction.DESC)})Pageable pageable) {
		Integer page=0;
		List<Audit> results = this.auditService.findAudits(page,pageable);
		model.put("pageNumber", pageable.getPageNumber());
		model.put("pageSize", pageable.getPageSize());
		model.put("hasPrevious", pageable.hasPrevious());
		Integer auditsPerPage = pageable.getPageSize();
		Double totalPages = Math.ceil(auditService.findAll().size()/(auditsPerPage+1));
		model.put("totalPages", totalPages);
		model.put("audits", results);
		return VIEWS_ADMIN_AUDITS;
	}
}