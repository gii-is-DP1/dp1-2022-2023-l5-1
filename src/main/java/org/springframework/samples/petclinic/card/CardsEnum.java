package org.springframework.samples.petclinic.card;

import java.util.List;

import org.springframework.samples.petclinic.game.MapGameRepository;
import org.springframework.samples.petclinic.player.Player;
import org.springframework.samples.petclinic.player.PlayerService;

public enum CardsEnum implements UseCardsInterface { // Primero estan todas las de descarte al usarlo
    //CARTA DE EJEMPLO
    DAIMIO("Daimio", "Hace 1 de daño a todos los jugadores") {
        @Override
        public void effectEndTurn(Player player, PlayerService playerService, MapGameRepository mapGameRepository) {
            for (Player play : player.getGame().getPlayers()) {
                if (!player.equals(play)) {
                    playerService.damagePlayer(play, 1);
                    playerService.savePlayer(play);
                }
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
