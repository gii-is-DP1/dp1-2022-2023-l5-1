package org.springframework.samples.minesweeper.genre;

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
public class GenreServiceTest {
	@Autowired
	private GenreService genreService;

	
	@Test
	@Transactional
	void saveGenreTest() {

		List<Genre>Genres =  new ArrayList<>(this.genreService.findGenres());
		Genre genre = Genres.get(0);

		String name1=genre.getName();
		genre.setName("Battlefield:2");
		genreService.saveGenre(genre);
	
		assertThat(genre.getName()).isNotEqualTo(name1);  
	}

	
	@Test
	void findGenreByIdTest() {
		List<Genre>Genres =  new ArrayList<>(this.genreService.findGenres());
	
		Genre Genre = Genres.get(0);
		String id=Genre.getId().toString();

		Genre Genre2 = genreService.findGenre(id);
	
		assertThat(Genre).isEqualTo(Genre2);  
	}
	
	@Test
	void findGenresTest() {
		List<Genre>genres =  new ArrayList<>(this.genreService.findGenres());
		Integer size = genres.size();
		Integer expectedSize=genreService.findGenres().size();	
		assertThat(size).isEqualTo(expectedSize);
	}
	
}