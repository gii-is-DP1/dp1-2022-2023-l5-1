package org.springframework.samples.petclinic.model;

import java.time.LocalDateTime;

import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.samples.petclinic.user.User;

@MappedSuperclass
public class AuditableEntity extends BaseEntity{

    @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm")
    public LocalDateTime creationDate;

    @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm")
    public LocalDateTime lastModified;

    @NotNull
    @ManyToOne
    public User user;
}
