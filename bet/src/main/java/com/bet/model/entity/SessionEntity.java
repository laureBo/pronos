package com.bet.model.entity;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity(name = "Session")
@Table(name = "SESSION")
@Getter
@Setter
public class SessionEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_session")
	private int idSession;

	@Column(name = "nom")
	private String nomSession;

	@Column(name = "date_creation")
	private Date dateCreationSession;

	@OneToMany(mappedBy = "session", cascade = CascadeType.ALL, orphanRemoval = true)
	@OrderBy("dateMatch")
	private List<MatchEntity> matchs;

	@ManyToOne
	@JoinColumn(name = "pseudo_createur")
	private UtilisateurEntity createur;

	@OneToMany(mappedBy = "participerId.session")
	private List<ParticiperEntity> participations;

}
