package org.springframework.samples.petclinic.card;

import org.springframework.samples.petclinic.game.MapGameRepository;
import org.springframework.samples.petclinic.player.Player;
import org.springframework.samples.petclinic.player.PlayerService;

public interface UseCardsInterface {
    static final String damageString="damage";

    default void effect(Player player,PlayerService playerService, MapGameRepository mapGameRepository) {
    }
    default void effectStartTurn(Player player,PlayerService playerService, MapGameRepository mapGameRepository) {
    }
    default void effectEndTurn(Player player,PlayerService playerService, MapGameRepository mapGameRepository) {
    }
    default Integer effectDamage(Player player,PlayerService playerService,Integer damage, MapGameRepository mapGameRepository) {
        return damage;
    }
}
