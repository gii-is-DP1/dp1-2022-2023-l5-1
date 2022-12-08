package org.springframework.samples.petclinic.player;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.card.Card;
import org.springframework.samples.petclinic.game.GameService;
import org.springframework.samples.petclinic.game.MapGameRepository;
import org.springframework.samples.petclinic.user.UserService;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private GameService gameService;
    @Autowired
    private PlayerService pService;
    @Autowired
    private MapGameRepository mapGameRepository;

    @Autowired
	public PlayerService(PlayerRepository playerRepository) {
		this.playerRepository = playerRepository;
	}
	
	@Transactional
    public Optional<Player> findPlayerById(int id){
        return playerRepository.findPlayerById(id);
    }

    @Transactional
    public Iterable<Player> findAll() {
        Iterable<Player> res = playerRepository.findAll();
        return res;
    }

    @Transactional
    public int playerCount() {
        return (int) playerRepository.count();
    }

	@Transactional
    public void savePlayer(Player player) {
        playerRepository.save(player);
    }

	// This function is called whenever any player is damaged
    @Transactional
    public void damagePlayer(Player player, Integer damage) {
        damage = useCardsInDamage(player, damage);
        Integer damagedLife = player.getResistance() - damage;
        Integer minHealth = 0;
        if (minHealth < damagedLife) {
            player.setResistance(damagedLife);
        } else {
            player.setResistance(minHealth);
			Integer minHonor = 0;
			Integer honor = player.getHonnor();
			Integer honorUpdate = honor - 1;
			// Esto tiene que ver con los turnos:
            //List<Integer> turnList = mapGameRepository.getTurnList(player.getGame().getId());
            //Integer positionInTurnList = turnList.indexOf(player.getId());
            // if (positionInTurnList >= 0) { // If the player is in the turnlist
            //     turnList.remove(player.getId());
            //     mapGameRepository.putTurnList(player.getGame().getId(), turnList);
            // }

			
			// Si un jugador pierde todos los puntos de honor la partida se acaba
			if (minHonor < honorUpdate){
				// FIN PARTIDA
			}

            player.setHonnor(honorUpdate);
        }
    }
	// Use all card from a player that are activated
	@Transactional
	public Integer useCardsInDamage(Player player, Integer damage) {
		for (Card card : player.getAvailableCards()) {
			damage = card.getCardEnum().effectDamage(player, pService, damage, mapGameRepository);
		}
		return damage;
	}
	


}
