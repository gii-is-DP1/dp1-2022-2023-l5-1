package org.springframework.samples.minesweeper.saga;

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

@WebMvcTest(controllers = SagaController.class,
excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
excludeAutoConfiguration = SecurityConfiguration.class)
@AutoConfigureMockMvc
public class SagaControllerTest {

    private static final String TEST_SAGA_ID = "1";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SagaService sagaService;

    @MockBean
    private SagaController sagaController;
    @Mock
    private Saga saga;

    @Test
	@WithMockUser(username="admin1",authorities={"admin"})
	void testshowSagaList() throws Exception {
		    mockMvc.perform(get("/sagas")).andExpect(status().is(200));
        
    }


    @Test
    @WithMockUser(username = "admin1", authorities = {"admin"})
    void testInitUpdateForm() throws Exception{
        when(this.sagaService.findSaga(TEST_SAGA_ID)).thenReturn(saga);
		mockMvc.perform(get("/sagas/update?sagaId={sagaID}",TEST_SAGA_ID)).andExpect(status().isOk());
    }
    
}
