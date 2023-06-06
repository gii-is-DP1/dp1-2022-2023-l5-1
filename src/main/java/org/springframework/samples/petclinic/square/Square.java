package org.springframework.samples.petclinic.square;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.samples.petclinic.board.Board;
import org.springframework.samples.petclinic.model.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Square extends BaseEntity{
    
    @NotNull
    @Max(16)
    @Min(0)
    public Integer row;

    @NotNull
    @Max(30)
    @Min(0)
    public Integer column;

    @NotNull
    public Boolean isMine;

    @NotNull
    public Boolean isFlag;

    @NotNull
    public Boolean isCovered;

    @NotNull
    @ManyToOne
    public Board board;
}
