package com.bet.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "PARI")
@Getter
@Setter
public class PariEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_pari")
	private int idPari;

	@Column(name = "pari_equipe1")
	private int equipe1;

	@Column(name = "pari_equipe2")
	private int equipe2;

	@ManyToOne
	@JoinColumn(name = "pseudo")
	private UtilisateurEntity utilisateur;

	@ManyToOne
	@JoinColumn(name = "id_match")
	private MatchEntity match;
}
