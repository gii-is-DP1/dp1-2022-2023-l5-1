package org.springframework.samples.petclinic.vet;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.samples.petclinic.model.NamedEntity;


@Entity
@Table(name = "specialties")
public class Specialty extends NamedEntity {

}
