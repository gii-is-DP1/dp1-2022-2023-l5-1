package org.springframework.samples.petclinic.game;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.user.User;

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

}
