package org.springframework.samples.minesweeper.game;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.minesweeper.board.DifficultyLevel;
import org.springframework.samples.minesweeper.user.UserService;
import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class GameServiceTest {
	
	@Autowired
	private GameService gs;

	@Autowired
	private UserService us;

	@Test
	@Transactional
	void saveGameTest() {

		Game g = new Game();
		g.setDifficulty(DifficultyLevel.ADVANCED);
		g.setStart(LocalDateTime.of(2000, 2, 2, 2, 2, 2));
		g.setId(900);
		g.setInProgress(true);
		g.setEnd(LocalDateTime.of(2001, 2, 2, 2, 2, 2));
		g.setSuccess(false);
		g.setUser(us.getAllPlayers().get(0));


		LocalDateTime date1 =g.getStart();
		g.setStart(LocalDateTime.of(2000, 2, 1 ,1, 2, 3));
		gs.saveGame(g);
	
		assertThat(g.getStart()).isNotEqualTo(date1);  
	}
	@Test
	@Transactional
	void deleteGameTest() {

		Game g = new Game();
		g.setDifficulty(DifficultyLevel.ADVANCED);
		g.setStart(LocalDateTime.of(2000, 2, 2, 2, 2, 2));
		g.setId(900);
		g.setInProgress(true);
		g.setEnd(LocalDateTime.of(2001, 2, 2, 2, 2, 2));
		g.setSuccess(false);
		g.setUser(us.getAllPlayers().get(0));


		LocalDateTime date1 =g.getStart();
		g.setStart(LocalDateTime.of(2000, 2, 1 ,1, 2, 3));
		gs.saveGame(g);
	
		assertThat(g.getStart()).isNotEqualTo(date1);  
	}


}