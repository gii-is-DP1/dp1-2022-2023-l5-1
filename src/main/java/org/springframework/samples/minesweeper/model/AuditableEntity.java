package org.springframework.samples.minesweeper.model;

import java.time.LocalDateTime;

import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.samples.minesweeper.user.User;

@MappedSuperclass
public class AuditableEntity extends BaseEntity{

    @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm")
    public LocalDateTime start;

    @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm")
    public LocalDateTime end;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    public User user;

    public LocalDateTime getStart(){
        return this.start;
    }

    public LocalDateTime getEnd(){
        return this.end;
    }

    public User getUser(){
        return this.user;
    }

    public void setStart(LocalDateTime s){
        this.start=s;
    }

    public void setEnd(LocalDateTime e){
        this.end=e;
    }

    public void setUser(User user){
        this.user=user;
    }
}
