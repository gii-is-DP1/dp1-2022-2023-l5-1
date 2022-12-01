package org.springframework.samples.petclinic.user;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User{
	@Id
	String username;

	@NotBlank
	private String password;

	boolean enabled;

	@Column(name="name")
	@NotBlank
	private String name;

	@Column(name="surname")
	@NotBlank
	private String surname;
	
	@Column(name="email")
    @NotBlank
	private String email;
	
	@Column(name="profpicurl")
	@NotBlank
	private String profpicurl;


	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private Set<Authorities> authorities;
}
