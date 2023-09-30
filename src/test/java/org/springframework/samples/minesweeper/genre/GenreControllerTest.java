package org.springframework.samples.minesweeper.genre;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.minesweeper.configuration.SecurityConfiguration;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = GenreController.class,
excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
excludeAutoConfiguration = SecurityConfiguration.class)
@AutoConfigureMockMvc
public class GenreControllerTest {

    private static final String TEST_GENRE_ID = "1";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GenreService genreService;

    @MockBean
    private GenreController genreController;
    @Mock
    private Genre genre;

    @Test
	@WithMockUser(username="admin1",authorities={"admin"})
	void testshowGenreList() throws Exception {
		    mockMvc.perform(get("/genres")).andExpect(status().is(200));
        
    }


    @Test
    @WithMockUser(username = "admin1", authorities = {"admin"})
    void testInitUpdateForm() throws Exception{
        when(this.genreService.findGenre(TEST_GENRE_ID)).thenReturn(genre);
		mockMvc.perform(get("/genres/update?genreId={genreID}",TEST_GENRE_ID)).andExpect(status().isOk());
    }
    
}
