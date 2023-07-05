package org.springframework.samples.minesweeper.model; 
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get; 
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;   
import org.junit.jupiter.api.BeforeEach; import org.junit.jupiter.api.Test; import org.mockito.Mock; import org.springframework.beans.factory.annotation.Autowired; import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest; import org.springframework.boot.test.mock.mockito.MockBean; import org.springframework.context.annotation.ComponentScan; import org.springframework.context.annotation.FilterType; import org.springframework.samples.minesweeper.configuration.SecurityConfiguration;
import org.springframework.samples.minesweeper.game.GameService;
import org.springframework.samples.minesweeper.genre.GenreService;
import org.springframework.samples.minesweeper.platform.PlatformService;
import org.springframework.samples.minesweeper.saga.SagaService;
import org.springframework.samples.minesweeper.user.AuthoritiesService;
import org.springframework.samples.minesweeper.user.User;
import org.springframework.samples.minesweeper.user.UserController;
import org.springframework.samples.minesweeper.user.UserService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer; 
import org.springframework.security.test.context.support.WithMockUser; 
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


@WebMvcTest(controllers = UserController.class,
excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
excludeAutoConfiguration = SecurityConfiguration.class)
public class UserControllerTest {

    @MockBean
    private UserService userService;
    @MockBean
    private AuthoritiesService authoritiesService;
    @MockBean
    private GameService gamaeService;
    @MockBean
    private SagaService gameService;
    @MockBean
    private GenreService genreService;
    @MockBean
    private PlatformService platformService;


    @Autowired
    private WebApplicationContext context;
    
    private MockMvc mockMvc;

    @Mock
    private User user;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders
                  .webAppContextSetup(context)
                  .apply(SecurityMockMvcConfigurers.springSecurity())
                  .build();
    }
 
    @Test
    @WithMockUser(username="admin",authorities={"admin"})
    void testUsersList() throws Exception {
        mockMvc.perform(get("/users")).andExpect(status().isOk());
    }

}