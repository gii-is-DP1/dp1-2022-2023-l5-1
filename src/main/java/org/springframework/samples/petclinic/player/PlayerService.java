package org.springframework.samples.petclinic.player;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.user.UserService;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {

    private PlayerRepository playerRepository;	
	
	@Autowired
	private UserService userService;

    @Autowired
	public PlayerService(PlayerRepository playerRepository) {
		this.playerRepository = playerRepository;
	}
	
	@Transactional
    public Optional<Player> findPlayerById(int id){
        return playerRepository.findPlayerById(id);
    }
}
