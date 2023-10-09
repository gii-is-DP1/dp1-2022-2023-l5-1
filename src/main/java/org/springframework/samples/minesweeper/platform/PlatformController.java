package org.springframework.samples.minesweeper.platform;

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
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class PlatformController {

  	private static final String VIEWS_PLATFORM_LIST = "platforms/listPlatform";
	private static final String VIEWS_PLATFORM_UPDATE_FORM = "platforms/updatePlatformForm";

	private final PlatformService platformService;
	private final PaginatingUtil paginatingUtil;

	@Autowired
	public PlatformController(PlatformService clinicService, PaginatingUtil pu) {
		this.platformService = clinicService;
		this.paginatingUtil = pu;
	}
    
    @GetMapping(value = { "/platforms" })
	public String showPlatformList(Map<String, Object> model,@PageableDefault(page = 0, size = 5)@SortDefault.SortDefaults({
		@SortDefault(sort = "id", direction = Sort.Direction.ASC),
		@SortDefault(sort = "name", direction = Sort.Direction.DESC)})Pageable pageable) {
		
		String type = "platforms";
		this.paginatingUtil.prepareModelForPagination(model, pageable, type,null);
		return VIEWS_PLATFORM_LIST;
	}

    @GetMapping(value = "/platforms/update")
	public String initUpdateForm(Map<String, Object> model, @RequestParam() String platformId) {
        Platform platform = this.platformService.findPlatform(platformId);
		model.put("platform", platform);
		log.info("Actualizando la plataforma {}",platform.getName());
		return VIEWS_PLATFORM_UPDATE_FORM;
	}
	@PostMapping(value = "/platforms/update")
	public String processUpdateForm(@Valid Platform platform, BindingResult result, @RequestParam String platformId) {
		if (result.hasErrors()) {
			return VIEWS_PLATFORM_UPDATE_FORM;
		}
		else {
            Integer id = Integer.valueOf(platformId);
            platform.setId(id);
			this.platformService.savePlatform(platform);
			log.info("Plataforma actualizada a {}",platform.getName());
			return "redirect:/platforms";
		}
	}
}
