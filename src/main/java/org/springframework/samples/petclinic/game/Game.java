package org.springframework.samples.petclinic.game;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.samples.petclinic.board.Board;
import org.springframework.samples.petclinic.deck.Deck;
import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.player.Player;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "games")
public class Game extends BaseEntity {
    //Codigo de Juego
    @Column(unique = true, name = "code")
    private String code;

    @Column(name="has_started")
    private boolean hasStarted;
    
    //Jugadores
    //Lista
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "games_players", joinColumns = @JoinColumn(name="game_id"), 
                inverseJoinColumns = @JoinColumn(name="player_id"))
    private List<Player> players;

    @ManyToOne(optional=false)
    private Player player;

    @OneToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.REMOVE}, optional=false)
    private Deck deck;


    @OneToOne(cascade = CascadeType.ALL)
    private Board board;


    public void addPlayerinPlayers(Player player){
    if(this.getPlayers()==null){
        List<Player> l = new ArrayList<>();
        l.add(player);
        this.setPlayers(l);     
    }else{
        List<Player> l = this.getPlayers();
        l.add(player);
        this.setPlayers(l);
    }
    
}
    //Borra un jugador de la lista.

    public void deletePlayerOfGame(Player player){
        this.players.remove(player);
    }
}