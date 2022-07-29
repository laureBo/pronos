package com.bet.model.entity;

import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "MATCH")
@Getter
@Setter
public class MatchEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_match")
	private int idMatch;

	@Column(name = "equipe1")
	private String equipe1;

	@Column(name = "equipe2")
	private String equipe2;

	@Column(name = "score_equipe1")
	private int scoreEquipe1;

	@Column(name = "score_equipe2")
	private int scoreEquipe2;

	@Column(name = "date_match")
	private Date dateMatch;

	@ManyToOne
	@JoinColumn(name = "id_session")
	private SessionEntity session;

	@OneToMany(mappedBy = "match")
	private List<PariEntity> paris;

}
