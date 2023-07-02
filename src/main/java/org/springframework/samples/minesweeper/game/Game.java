package org.springframework.samples.minesweeper.game;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.springframework.samples.minesweeper.board.DifficultyLevel;
import org.springframework.samples.minesweeper.model.BaseEntity;
import org.springframework.samples.minesweeper.user.User;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Game extends BaseEntity{

    @NotNull
    @ManyToOne
    public User user;

    @NotNull
    public Boolean inProgress;

    public Boolean success;

    public DifficultyLevel difficulty;

}
