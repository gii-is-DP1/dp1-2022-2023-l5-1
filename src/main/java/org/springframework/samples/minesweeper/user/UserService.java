/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.minesweeper.user;


import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.samples.minesweeper.game.Game;
import org.springframework.samples.minesweeper.model.AuditableEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Mostly used as a facade for all Minesweeper controllers Also a placeholder
 * for @Transactional and @Cacheable annotations
 *
 * @author Michael Isvy
 */
@Service
public class UserService {

	private UserRepository userRepository;

	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Transactional
	public void saveUser(User user) throws DataAccessException {
		userRepository.save(user);
	}
	
	public Optional<User> findUser(String username) {
		return userRepository.findById(username);
	}

	@Transactional(readOnly = true)
	public User findUserByUsername(String username) throws DataAccessException {
		return userRepository.findByUsername(username);
	}

	@Transactional(readOnly = true)	
	public Collection<User> findUsers() throws DataAccessException {
		return userRepository.findAll();
	}

	@Transactional
	public void deleteUser(String username) {
		userRepository.deleteById(username); 
	}

    public List<User> getAllPlayers() {
		return userRepository.findAllPlayers();
	}
		
	public List<User> getAllAdmins() {
		return userRepository.findAllAdmins();
	}

	public List<Game> getAllGameByUsername(User user) {
		return userRepository.findAllGamesByPlayer(user);
	}	
    	
}
