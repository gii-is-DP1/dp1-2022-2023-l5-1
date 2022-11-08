package org.springframework.samples.petclinic.player;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.samples.petclinic.configuration.SecurityConfiguration;
import org.springframework.samples.petclinic.player.Player;
import org.springframework.samples.petclinic.player.PlayerController;
import org.springframework.samples.petclinic.player.PlayerService;
import org.springframework.samples.petclinic.user.AuthoritiesService;
import org.springframework.samples.petclinic.user.UserService;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

/**
 * Test class for {@link PlayerController}
 *
 * @author Colin But
 */

@WebMvcTest(controllers = PlayerController.class, excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = WebSecurityConfigurer.class), excludeAutoConfiguration = SecurityConfiguration.class)
class PlayerControllerTests {

	private static final int TEST_OWNER_ID = 1;

	@Autowired
	private PlayerController playerController;

	@MockBean
	private PlayerService clinicService;

	@MockBean
	private UserService userService;

	@MockBean
	private AuthoritiesService authoritiesService;

	@Autowired
	private MockMvc mockMvc;

	private Player george;

	@BeforeEach
	void setup() {

		george = new Player();
		george.setId(TEST_OWNER_ID);
		george.setFirstName("George");
		george.setLastName("Franklin");
		george.setAddress("110 W. Liberty St.");
		george.setCity("Madison");
		george.setTelephone("6085551023");
		given(this.clinicService.findPlayerById(TEST_OWNER_ID)).willReturn(george);

	}

	@WithMockUser(value = "spring")
	@Test
	void testInitCreationForm() throws Exception {
		mockMvc.perform(get("/players/new")).andExpect(status().isOk()).andExpect(model().attributeExists("player"))
				.andExpect(view().name("players/createOrUpdatePlayerForm"));
	}

	@WithMockUser(value = "spring")
	@Test
	void testProcessCreationFormSuccess() throws Exception {
		mockMvc.perform(post("/players/new").param("firstName", "Joe").param("lastName", "Bloggs").with(csrf())
				.param("address", "123 Caramel Street").param("city", "London").param("telephone", "01316761638"))
				.andExpect(status().is3xxRedirection());
	}

	@WithMockUser(value = "spring")
	@Test
	void testProcessCreationFormHasErrors() throws Exception {
		mockMvc.perform(post("/players/new").with(csrf()).param("firstName", "Joe").param("lastName", "Bloggs")
				.param("city", "London")).andExpect(status().isOk()).andExpect(model().attributeHasErrors("player"))
				.andExpect(model().attributeHasFieldErrors("player", "address"))
				.andExpect(model().attributeHasFieldErrors("player", "telephone"))
				.andExpect(view().name("players/createOrUpdatePlayerForm"));
	}

	@WithMockUser(value = "spring")
	@Test
	void testInitFindForm() throws Exception {
		mockMvc.perform(get("/players/find")).andExpect(status().isOk()).andExpect(model().attributeExists("player"))
				.andExpect(view().name("players/findPlayers"));
	}

	@WithMockUser(value = "spring")
	@Test
	void testProcessFindFormSuccess() throws Exception {
		given(this.clinicService.findPlayerByLastName("")).willReturn(Lists.newArrayList(george, new Player()));

		mockMvc.perform(get("/players")).andExpect(status().isOk()).andExpect(view().name("players/playersList"));
	}

	@WithMockUser(value = "spring")
	@Test
	void testProcessFindFormByLastName() throws Exception {
		given(this.clinicService.findPlayerByLastName(george.getLastName())).willReturn(Lists.newArrayList(george));

		mockMvc.perform(get("/players").param("lastName", "Franklin")).andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/players/" + TEST_OWNER_ID));
	}

	@WithMockUser(value = "spring")
	@Test
	void testProcessFindFormNoPlayersFound() throws Exception {
		mockMvc.perform(get("/players").param("lastName", "Unknown Surname")).andExpect(status().isOk())
				.andExpect(model().attributeHasFieldErrors("player", "lastName"))
				.andExpect(model().attributeHasFieldErrorCode("player", "lastName", "notFound"))
				.andExpect(view().name("players/findPlayers"));
	}

	@WithMockUser(value = "spring")
	@Test
	void testInitUpdatePlayerForm() throws Exception {
		mockMvc.perform(get("/players/{playerId}/edit", TEST_OWNER_ID)).andExpect(status().isOk())
				.andExpect(model().attributeExists("player"))
				.andExpect(model().attribute("player", hasProperty("lastName", is("Franklin"))))
				.andExpect(model().attribute("player", hasProperty("firstName", is("George"))))
				.andExpect(model().attribute("player", hasProperty("address", is("110 W. Liberty St."))))
				.andExpect(model().attribute("player", hasProperty("city", is("Madison"))))
				.andExpect(model().attribute("player", hasProperty("telephone", is("6085551023"))))
				.andExpect(view().name("players/createOrUpdatePlayerForm"));
	}

	@WithMockUser(value = "spring")
	@Test
	void testProcessUpdatePlayerFormSuccess() throws Exception {
		mockMvc.perform(post("/players/{playerId}/edit", TEST_OWNER_ID).with(csrf()).param("firstName", "Joe")
				.param("lastName", "Bloggs").param("address", "123 Caramel Street").param("city", "London")
				.param("telephone", "01616291589")).andExpect(status().is3xxRedirection())
				.andExpect(view().name("redirect:/players/{playerId}"));
	}

	@WithMockUser(value = "spring")
	@Test
	void testProcessUpdatePlayerFormHasErrors() throws Exception {
		mockMvc.perform(post("/players/{playerId}/edit", TEST_OWNER_ID).with(csrf()).param("firstName", "Joe")
				.param("lastName", "Bloggs").param("city", "London")).andExpect(status().isOk())
				.andExpect(model().attributeHasErrors("player"))
				.andExpect(model().attributeHasFieldErrors("player", "address"))
				.andExpect(model().attributeHasFieldErrors("player", "telephone"))
				.andExpect(view().name("players/createOrUpdatePlayerForm"));
	}

	@WithMockUser(value = "spring")
	@Test
	void testShowPlayer() throws Exception {
		mockMvc.perform(get("/players/{playerId}", TEST_OWNER_ID)).andExpect(status().isOk())
				.andExpect(model().attribute("player", hasProperty("lastName", is("Franklin"))))
				.andExpect(model().attribute("player", hasProperty("firstName", is("George"))))
				.andExpect(model().attribute("player", hasProperty("address", is("110 W. Liberty St."))))
				.andExpect(model().attribute("player", hasProperty("city", is("Madison"))))
				.andExpect(model().attribute("player", hasProperty("telephone", is("6085551023"))))
				.andExpect(view().name("players/playerDetails"));
	}

}
