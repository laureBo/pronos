package com.bet.model.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity(name = "Utilisateur")
@Table(name = "UTILISATEUR")
@Getter
@Setter
public class UtilisateurEntity {

	private static final long serialVersionUID = -2743277015830516885L;

	@Id
	@Column(name = "pseudo")
	private String pseudoUser;

	@Column(name = "password")
	private String password;

	@Column(name = "mail")
	private String mailUser;

	@Column(name = "nom")
	private String nomUser;

	@Column(name = "prenom")
	private String prenomUser;

	@OneToMany(mappedBy = "createur")
	private List<SessionEntity> sessions;

	@OneToMany(mappedBy = "utilisateur")
	private List<PariEntity> paris;

	@OneToMany(mappedBy = "utilisateur")
	private List<ParticiperEntity> participations;

}
