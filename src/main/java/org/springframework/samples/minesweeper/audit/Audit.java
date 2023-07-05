package org.springframework.samples.minesweeper.audit;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.samples.minesweeper.board.DifficultyLevel;
import org.springframework.samples.minesweeper.model.BaseEntity;
import org.springframework.samples.minesweeper.user.User;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "audits")
public class Audit extends BaseEntity {
	
	@Column(name = "start_date")        
	@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
	private LocalDateTime startDate;
	
	@Column(name = "end_date")        
	@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:ss")
	private LocalDateTime endDate;
	
	@ManyToOne
	@NotNull
	private User user;
	
	@Column(name="in_progress")
	private Boolean inProgress;

	private Boolean success;
	
	private DifficultyLevel difficulty;


	
}