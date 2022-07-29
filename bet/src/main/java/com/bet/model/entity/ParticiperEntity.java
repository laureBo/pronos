package com.bet.model.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity(name = "Participer")
@Table(name = "PARTICIPER")
@IdClass(ParticiperIdEntity.class)
@Getter
@Setter
public class ParticiperEntity {

	@Id
	private UtilisateurEntity utilisateur;

	@Id
	private SessionEntity session;
}
