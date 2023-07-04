package org.springframework.samples.minesweeper.game;

import java.time.Duration;

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

    public Integer getDuration() {
        Long time = Duration.between(this.getCreationDate(),this.getLastModified()).toSeconds();
        return Integer.valueOf(time.toString());
    } 

}
