package org.springframework.samples.minesweeper.platform;

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

@WebMvcTest(controllers = PlatformController.class,
excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class),
excludeAutoConfiguration = SecurityConfiguration.class)
@AutoConfigureMockMvc
public class PlatformControllerTest {

    private static final String TEST_PLATFORM_ID = "1";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PlatformService platformService;

    @MockBean
    private PlatformController platformController;
    @Mock
    private Platform platform;

    @Test
	@WithMockUser(username="admin1",authorities={"admin"})
	void testshowPlatformList() throws Exception {
		    mockMvc.perform(get("/platforms")).andExpect(status().is(200));
        
    }


    @Test
    @WithMockUser(username = "admin1", authorities = {"admin"})
    void testInitUpdateForm() throws Exception{
        when(this.platformService.findPlatform(TEST_PLATFORM_ID)).thenReturn(platform);
		mockMvc.perform(get("/platforms/update?platformId={platformID}",TEST_PLATFORM_ID)).andExpect(status().isOk());
    }
    
}

