package org.springframework.samples.minesweeper.genre;

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

@Controller
public class GenreController {

  	private static final String VIEWS_GENRE_LIST = "genres/listGenre";
	private static final String VIEWS_GENRE_UPDATE_FORM = "genres/updateGenreForm";

	private final GenreService genreService;

	@Autowired
	public GenreController(GenreService clinicService) {
		this.genreService = clinicService;
	}
    
    @GetMapping(value = { "/genres" })
	public String showGenreList(Map<String, Object> model) {
		
		List<Genre> genres = new ArrayList<>(this.genreService.findGenres());

		model.put("genres", genres);
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
