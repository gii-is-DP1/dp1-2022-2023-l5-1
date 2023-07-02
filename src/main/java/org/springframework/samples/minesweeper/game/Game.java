package org.springframework.samples.minesweeper.game;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import org.springframework.samples.minesweeper.board.DifficultyLevel;
import org.springframework.samples.minesweeper.model.AuditableEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Game extends AuditableEntity{

    @NotNull
    public Boolean inProgress;

    public Boolean success;

    public DifficultyLevel difficulty;

}
