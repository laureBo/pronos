package com.bet.model.entity;

import java.io.Serializable;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParticiperIdEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@ManyToOne
	@JoinColumn(name = "pseudo")
	private UtilisateurEntity utilisateur;

	@ManyToOne
	@JoinColumn(name = "id_session")
	private SessionEntity session;

}
