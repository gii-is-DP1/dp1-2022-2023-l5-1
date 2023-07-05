package org.springframework.samples.minesweeper.genre;


import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Mostly used as a facade for all Minesweeper controllers Also a placeholder
 * for @Transactional and @Cacheable annotations
 *
 * @author Michael Isvy
 */
@Service
public class GenreService {

	private GenreRepository genreRepository;

	@Autowired
	public GenreService(GenreRepository genreRepository) {
		this.genreRepository = genreRepository;
	}

	@Transactional
	public void saveGenre(Genre genre) throws DataAccessException {
		genreRepository.save(genre);
	}
	
	public Genre findGenre(String genre) {
		return genreRepository.findGenreById(Integer.valueOf(genre));
	}

	@Transactional(readOnly = true)	
	public Collection<Genre> findGenres() throws DataAccessException {
		return (Collection<Genre>) genreRepository.findAll();
	}
    	
}