package org.springframework.samples.petclinic.card;

import java.util.List;

import org.springframework.samples.petclinic.game.MapGameRepository;
import org.springframework.samples.petclinic.player.Player;
import org.springframework.samples.petclinic.player.PlayerService;

public enum CardsEnum implements UseCardsInterface { // Primero estan todas las de descarte al usarlo
    //POR TERMINAR, HACER DAIMIO
    GOURMET("Gourmet", "Cuando consigas 3 o más dados 'ONE', recibirás 2 puntos de victoria extra") {
        @Override
        public void effectInRoll(Player player, PlayerService playerService, MapGameRepository mapGameRepository) {
            Roll roll = mapGameRepository.getRoll(player.getGame().getId());

            Map<String, Integer> rollValues = playerService.countRollValues(roll.getValues());
            if (rollValues.get("ones") > 2) {
                player.setVictoryPoints(player.getVictoryPoints() + 2);
                playerService.savePlayer(player);
            }

        }
    };

    private String name;
    private String description;

    private CardsEnum(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public void setDescription(String newDescription) {
        this.description = newDescription;
    }

}
