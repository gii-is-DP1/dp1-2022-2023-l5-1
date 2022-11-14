package org.springframework.samples.petclinic.board;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Positive;

import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.player.Player;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="boards")
public class Board extends BaseEntity{
    String background;
    @Positive
    int width;
    @Positive
    int height;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Player> players;


    public Board(){

        this.background="/resources/static/images/pets.png";
        this.width=527;
        this.height=644;
    }

    public Board(List<Player> l){
        this.background="/resources/static/images/pets.png";
        this.width=527;
        this.height=644;
        this.players=l;
    }

}
