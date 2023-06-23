package org.springframework.samples.petclinic.model;

import java.time.LocalDateTime;

import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.samples.petclinic.user.User;

import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
public class AuditableEntity extends BaseEntity{

    @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm")
    public LocalDateTime creationDate;

    @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm")
    public LocalDateTime lastModified;

    @NotNull
    @ManyToOne
    public User user;

    public LocalDateTime getCreationDate(){
        return this.creationDate;
    }

    public LocalDateTime getLastModified(){
        return this.lastModified;
    }

    public User getUser(){
        return this.user;
    }

    public void setCreationDate(LocalDateTime cd){
        this.creationDate=cd;
    }

    public void setLastModified(LocalDateTime lm){
        this.lastModified=lm;
    }

    public void setUser(User user){
        this.user=user;
    }
}
