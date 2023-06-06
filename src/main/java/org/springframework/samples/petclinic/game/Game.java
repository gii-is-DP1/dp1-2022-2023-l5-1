package org.springframework.samples.petclinic.game;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.springframework.samples.petclinic.board.Board;
import org.springframework.samples.petclinic.model.AuditableEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Game extends AuditableEntity{
    @NotNull
    public Boolean inProgress;

    @NotNull
    @Positive
    public Float time;

    @NotNull
    @Positive
    public Integer numberOfClicks;

    @OneToOne
    @NotNull
    public Board board;
}
