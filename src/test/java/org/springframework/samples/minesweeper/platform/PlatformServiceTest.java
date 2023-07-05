package org.springframework.samples.minesweeper.platform;

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
public class PlatformServiceTest {
	@Autowired
	private PlatformService platformService;

	
	@Test
	@Transactional
	void saveplatformTest() {

		List<Platform>platforms =  new ArrayList<>(this.platformService.findPlatforms());
		Platform platform = platforms.get(0);

		String name1=platform.getName();
		platform.setName("Battlefield:2");
		platformService.savePlatform(platform);
	
		assertThat(platform.getName()).isNotEqualTo(name1);  
	}

	
	@Test
	void findPlatformByIdTest() {
		List<Platform>platforms =  new ArrayList<>(this.platformService.findPlatforms());
	
		Platform platform = platforms.get(0);
		String id=platform.getId().toString();

		Platform platform2 = platformService.findPlatform(id);
	
		assertThat(platform).isEqualTo(platform2);  
	}
	
	@Test
	void findPlatformsTest() {
		List<Platform>platforms =  new ArrayList<>(this.platformService.findPlatforms());
		Integer size = platforms.size();
		Integer expectedSize=platformService.findPlatforms().size();	
		assertThat(size).isEqualTo(expectedSize);
	}
	
}