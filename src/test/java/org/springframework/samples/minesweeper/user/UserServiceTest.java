package org.springframework.samples.minesweeper.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;

import org.springframework.stereotype.Service;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class UserServiceTest {
	@Autowired
	private UserService userService;

	
	@Test
	@Transactional
	void createUserTest() {
		
		Collection<User> users = this.userService.findUsers();
		int found = users.size();

		User p = new User();
		p.setName("mike");
		p.setEnabled(true);
		p.setHardcoregamer(true);
		p.setBiography("Biografia");
		p.setBirthDate(LocalDate.of(2000, 1, 1));
		p.setLocation("NoWhere");

		

		p.setProfilePicture("https://www.copahost.com/blog/wp-content/uploads/2019/07/imgsize2.png");

		p.setUsername("mike123");
		p.setPassword("mike123");
		

		this.userService.saveUser(p);
		assertThat(p.getUsername()).isNotBlank();
		assertThat(p.getUsername()).isNotEmpty();

		Optional<User> userFound; 
		userFound = this.userService.findUser("mike123");
		assertNotNull(userFound);
		users = this.userService.findUsers();
		assertThat(users.size()).isEqualTo(found + 1);  
	}
	

	@Test
	@Transactional
	void saveUserTest() {
		
		Collection<User> list1 = this.userService.findUsers();
		int size1 = list1.size();
		
		User p = new User();
		p.setName("mike");
		p.setEnabled(true);
		p.setHardcoregamer(true);
		p.setBiography("Biografia");
		p.setBirthDate(LocalDate.of(2000, 1, 1));
		p.setLocation("NoWhere");
		p.setProfilePicture("https://www.copahost.com/blog/wp-content/uploads/2019/07/imgsize2.png");

		p.setUsername("mike123");
		p.setPassword("mike123");
		

		this.userService.saveUser(p);
		assertThat(p.getUsername()).isNotBlank();
		assertThat(p.getUsername()).isNotEmpty();
		
		Collection<User> list2 = this.userService.findUsers();
		int size2 = list2.size();
		
		assertThat(size2>size1);
	}
	
	@Test
	void findUserByUsernameTest() {
		User p = new User();
		p.setName("mike");
		p.setEnabled(true);
		p.setHardcoregamer(true);
		p.setBiography("Biografia");
		p.setBirthDate(LocalDate.of(2000, 1, 1));
		p.setLocation("NoWhere");
		p.setProfilePicture("https://www.copahost.com/blog/wp-content/uploads/2019/07/imgsize2.png");

		p.setUsername("mike123");
		p.setPassword("mike123");
		this.userService.saveUser(p);
		
		Optional<User> p2 = this.userService.findUser("mike123");

		assertNotNull(p2);
		assertEquals(p.getUsername(), p2.get().getUsername()); 	
	}
	

	@Test
	@Transactional
	void deleteUserTest() {
		Collection<User> list1 = this.userService.findUsers();
		int size1 = list1.size();
		
		User p = new User();
		p.setName("mike");
		p.setEnabled(true);
		p.setHardcoregamer(true);
		p.setBiography("Biografia");
		p.setBirthDate(LocalDate.of(2000, 1, 1));
		p.setLocation("NoWhere");
		p.setProfilePicture("https://www.copahost.com/blog/wp-content/uploads/2019/07/imgsize2.png");

		p.setUsername("mike123");
		p.setPassword("mike123");
		

		this.userService.saveUser(p);
		assertThat(p.getUsername()).isNotBlank();
		assertThat(p.getUsername()).isNotEmpty(); //created correctly 
		
		this.userService.deleteUser("mike123"); 

		Collection<User> list2 = this.userService.findUsers();
		int size2 = list2.size();
		Optional<User> user = userService.findUser("mike123");

		assertThat(size2==size1);
		assertThat(user.isEmpty());
	}

	@Test
	void findUsersNotAdminTest() {
		Collection<User> list1 = this.userService.getAllPlayers();
        assertTrue(list1.stream().noneMatch(user -> user.getAuthorities().stream().map(s->s.getAuthority()).anyMatch(a->a.equals("admin"))),"admins");

	}
	
	@Test
	void findUsersAdminTest() {
		Collection<User> list1 = this.userService.getAllAdmins();
        assertTrue(list1.stream().allMatch(user -> user.getAuthorities().stream().map(s->s.getAuthority()).anyMatch(a->a.equals("admin"))),"admins");

	}

}