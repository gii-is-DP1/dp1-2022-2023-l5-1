package org.springframework.samples.minesweeper.genre;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
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

@Controller
public class GenreController {

  	private static final String VIEWS_GENRE_LIST = "genres/listGenre";
	private static final String VIEWS_GENRE_UPDATE_FORM = "genres/updateGenreForm";

	private final GenreService genreService;

	private final PaginatingUtil paginatingUtil;

	@Autowired
	public GenreController(GenreService clinicService, PaginatingUtil pu) {
		this.genreService = clinicService;
		this.paginatingUtil = pu;
	}

    
    @GetMapping(value = { "/genres" })
	public String showGenreList(Map<String, Object> model,HttpServletRequest request, @PageableDefault(page = 0, size = 5)@SortDefault.SortDefaults({
		@SortDefault(sort = "id", direction = Sort.Direction.ASC),
		@SortDefault(sort = "name", direction = Sort.Direction.DESC)})Pageable pageable) {
		Integer page = 0;
		List ls=genreService.getAllGenresOrdered(page, pageable);
        Integer totalElements = genreService.findGenres().size();
		this.paginatingUtil.prepareModelForPagination(model, pageable, ls, totalElements);
		return VIEWS_GENRE_LIST;
	}

    @GetMapping(value = "/genres/update")
	public String initUpdateForm(Map<String, Object> model, @RequestParam() String genreId) {
        Genre genre = this.genreService.findGenre(genreId);
		model.put("genre", genre);
		return VIEWS_GENRE_UPDATE_FORM;
	}
	@PostMapping(value = "/genres/update")
	public String processUpdateForm(@Valid Genre genre, BindingResult result, @RequestParam String genreId) {
		if (result.hasErrors()) {
			return VIEWS_GENRE_UPDATE_FORM;
		}
		else {
            Integer id = Integer.valueOf(genreId);
            genre.setId(id);
			this.genreService.saveGenre(genre);
			return "redirect:/genres";
		}
	}
}
