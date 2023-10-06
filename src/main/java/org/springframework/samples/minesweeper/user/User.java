package org.springframework.samples.minesweeper.user;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.URL;
import org.springframework.core.style.ToStringCreator;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.samples.minesweeper.genre.Genre;
import org.springframework.samples.minesweeper.platform.Platform;
import org.springframework.samples.minesweeper.saga.Saga;

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

	@NotNull
	boolean hardcoregamer;

	String biography;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	LocalDate birthDate;

	@NotBlank
	String location;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="genre_id")
	Genre genre;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="saga_id")
	Saga saga;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="platform_id")
	Platform platform;

	@URL
	String profilePicture;

	@Version
	Integer version;

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
