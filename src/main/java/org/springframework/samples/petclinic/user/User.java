package org.springframework.samples.petclinic.user;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.springframework.core.style.ToStringCreator;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="users")
public class User{
	@Id
	@NotEmpty
	public
	String username;
	
	@NotBlank
	String password;

	@NotBlank
	String name;
	
	boolean enabled;

	@OneToMany(cascade=CascadeType.ALL, mappedBy="user")
	private Set<Authorities> authorities;

	public boolean isNew() {
		return this.username == null;
	}

	@Override
	public String toString() {
		return new ToStringCreator(this)

				.append("new", this.isNew()).append("name", this.getName())
				.toString();
	}

	public void setId(String userId) {
		this.username=userId;
	}	
	
}
