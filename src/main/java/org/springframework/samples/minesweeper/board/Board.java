package org.springframework.samples.minesweeper.board;

import javax.persistence.Entity;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.samples.minesweeper.model.AuditableEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Board extends AuditableEntity{

    @NotNull
    @Max(30)
    @Min(8)
    public Integer columns;

    @NotNull
    @Max(16)
    @Min(8)
    public Integer rows;

    @NotNull
    @Max(99)
    @Min(10)
    public Integer minesNumber;

    @NotNull
    @Max(99)
    @Min(10)
    public Integer flagsNumber;

    @NotNull
    public DifficultyLevel level;
}
