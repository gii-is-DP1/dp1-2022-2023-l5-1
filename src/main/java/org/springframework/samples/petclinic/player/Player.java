package org.springframework.samples.petclinic.player;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.samples.petclinic.card.Card;
import org.springframework.samples.petclinic.game.Game;
import org.springframework.samples.petclinic.hand.Hand;
import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.playercard.PlayerCard;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "players")
@Getter
@Setter
public class Player extends BaseEntity{
    
	@NotBlank
	private String nickname;

	@NotNull
	private Integer honnor;

	@NotNull
	private Integer resistance;

	@NotBlank
	private String rol;

	@NotBlank
	private String heroImg;

	@OneToMany(mappedBy = "player")
    private List<PlayerCard> mano;

	@ManyToOne(optional = false)
    @JoinColumn(name = "game_id")
    private Game game;

	public List<Card> getAvailableCards(){
        return this.mano.stream()
                    .filter(pc -> pc.getDiscarded().equals(Boolean.FALSE))
                    .map(pc -> pc.getCard())
                    .collect(Collectors.toList());
    }

}
