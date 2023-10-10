package org.springframework.samples.minesweeper.user; 
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get; 
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach; import org.junit.jupiter.api.Test; import org.mockito.Mock; import org.springframework.beans.factory.annotation.Autowired; import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest; import org.springframework.boot.test.mock.mockito.MockBean; import org.springframework.context.annotation.ComponentScan; import org.springframework.context.annotation.FilterType;
import org.springframework.samples.minesweeper.audit.AuditService;
import org.springframework.samples.minesweeper.configuration.SecurityConfiguration;
import org.springframework.samples.minesweeper.customComponents.PaginatingUtil;
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
includeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = PaginatingUtil.class),
excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
excludeAutoConfiguration = SecurityConfiguration.class)
public class UserControllerTest {
    private static final int TEST_PLAYER_ID = 1;
    private static final String TEST_PLAYER_NAME = "player1";

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
    @MockBean
    private AuditService auditService;

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
    void testUsersListByAdmin() throws Exception {
        mockMvc.perform(get("/users")).andExpect(status().isOk());
    }
    @Test
    @WithMockUser(username="player",authorities={"player"})
    void testUsersListByPlayer() throws Exception {
        mockMvc.perform(get("/users")).andExpect(status().isOk());
    }
    @Test
    void testUsersListByPlayerByAnonimous() throws Exception {
        mockMvc.perform(get("/users")).andExpect(status().is(401));
    }

    @Test
	@WithMockUser(username="player",authorities={"player"})
	void testNewUser() throws Exception {
		mockMvc.perform(get("/users/new")).andExpect(status().isOk());
	}
    @Test
	@WithMockUser(username="admin",authorities={"admin"})
	void testDeletePlayer() throws Exception {
		mockMvc.perform(get("/users/delete/{username}",TEST_PLAYER_NAME)).andExpect(status().is(302));
	}

    	
	@Test
	@WithMockUser(username="player",authorities={"player"})
	void testUpdatePlayer() throws Exception {
		when(this.userService.findUser(TEST_PLAYER_NAME)).thenReturn(Optional.of(user));
		mockMvc.perform(get("/users/update/{username}",TEST_PLAYER_ID)).andExpect(status().isOk());
	}
	
}