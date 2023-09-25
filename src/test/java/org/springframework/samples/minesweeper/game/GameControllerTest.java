package org.springframework.samples.minesweeper.game;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.minesweeper.audit.Audit;
import org.springframework.samples.minesweeper.audit.AuditService;
import org.springframework.samples.minesweeper.board.BoardService;
import org.springframework.samples.minesweeper.configuration.SecurityConfiguration;
import org.springframework.samples.minesweeper.user.UserService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import java.util.Optional;


@WebMvcTest(controllers = GameController.class,
excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
excludeAutoConfiguration = SecurityConfiguration.class)
@AutoConfigureMockMvc
public class GameControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GameService gameService;

    @MockBean
    private BoardService boardService;

    @MockBean
    private UserService userService;

    @MockBean
    private AuditService auditService;

    


    @Test
	@WithMockUser(username="admin1",authorities={"admin"})
	void testGameRules() throws Exception {
		mockMvc.perform(get("/gameRules")).andExpect(status().is(200));
	}

    @Test
	@WithMockUser(username="player1",authorities={"player"})
	void testGameRules2() throws Exception {
		mockMvc.perform(get("/gameRules")).andExpect(status().is(200));
	}

    @Test
    @WithMockUser(username="admin1",authorities={"admin"})
    public void testCreateGame() throws Exception {
        String id = "1";
        String success = "true";
        String audit_id = "2";

        
        Game mockGame = new Game();
        mockGame.setInProgress(true); // Simula que el juego está en progreso
        when(gameService.getGameById(anyInt())).thenReturn(mockGame);

        
        Audit mockAudit = new Audit();
        when(auditService.findAuditById(anyInt())).thenReturn(Optional.of(mockAudit));
        

        // Realizar la solicitud POST simulada
        mockMvc.perform(get("/games/endGame")
                .param("id", id)
                .param("success", success)
                .param("audit_id", audit_id))
                .andExpect(status().isOk())
                .andExpect(view().name("games/endGame"))
                .andExpect(model().attribute("success", true));

        
        verify(gameService, times(1)).getGameById(eq(1));
        verify(gameService, times(1)).saveGame(any(Game.class));
        verify(auditService, times(1)).findAuditById(eq(2));
        verify(auditService, times(1)).save(any(Audit.class));
    }

    @Test
    @WithMockUser(username="player1",authorities={"player"})
    public void testCreateGame1() throws Exception {
        
        String id = "1";
        String success = "true";
        String audit_id = "2";

        Game mockGame = new Game();
        mockGame.setInProgress(true); // Simula que el juego está en progreso
        when(gameService.getGameById(anyInt())).thenReturn(mockGame);

        
        Audit mockAudit = new Audit();
        when(auditService.findAuditById(anyInt())).thenReturn(Optional.of(mockAudit));
        
        mockMvc.perform(get("/games/endGame")
                .param("id", id)
                .param("success", success)
                .param("audit_id", audit_id))
                .andExpect(status().isOk())
                .andExpect(view().name("games/endGame"))
                .andExpect(model().attribute("success", true));

        verify(gameService, times(1)).getGameById(eq(1));
        verify(gameService, times(1)).saveGame(any(Game.class));
        verify(auditService, times(1)).findAuditById(eq(2));
        verify(auditService, times(1)).save(any(Audit.class));
    }

    @Test
    @WithMockUser(username="admin1",authorities={"admin"})
    public void testNewGamePage() throws Exception {

        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/games")
        );
        
        result.andExpect(status().isOk());

    }

    @Test
    @WithMockUser(username="player1",authorities={"player"})
    public void testNewGamePagePlayer() throws Exception {

        ResultActions result = mockMvc.perform(MockMvcRequestBuilders.get("/games")
        );
        
        result.andExpect(status().isOk());

    }

    @Test
	@WithMockUser(username="admin1",authorities={"admin"})
	void testshowActiveGamesList() throws Exception {
        if (gameService.getActiveGames().size()!=0){
		    mockMvc.perform(get("games/activeGames")).andExpect(status().is(200));
        }
        else{
            mockMvc.perform(get("games/activeGames")).andExpect(status().is(404));    
        }
    }

    @Test
	@WithMockUser(username="player1",authorities={"player"})
	void testPlayerDontshowActiveGamesList() throws Exception {
        if (gameService.getFinishGames().size()!=0){
		    mockMvc.perform(get("games/activeGames")).andExpect(status().is(403));
        }
        else{
            mockMvc.perform(get("games/activeGames")).andExpect(status().is(404));    
        }    
    }

    @Test
	@WithMockUser(username="admin1",authorities={"admin"})
	void testshowFinishGamesList() throws Exception {
        if (gameService.getFinishGames().size()!=0){
		    mockMvc.perform(get("games/finishGames")).andExpect(status().is(200));
        }
        else{
            mockMvc.perform(get("games/finishGames")).andExpect(status().is(404));    
        }
    }

    @Test
	@WithMockUser(username="player1",authorities={"player"})
	void testPlayerDontshowFinishGamesList() throws Exception {
		if (gameService.getFinishGames().size()!=0){
		    mockMvc.perform(get("games/finishGames")).andExpect(status().is(403));
        }
        else{
            mockMvc.perform(get("games/finishGames")).andExpect(status().is(404));    
        }
    }
}
