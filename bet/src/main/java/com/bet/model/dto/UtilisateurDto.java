package com.bet.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class UtilisateurDto {

	@JsonProperty(required = true)
	private String pseudo;

	@JsonProperty(required = true)
	private String mail;

	@JsonProperty(required = true)
	private String nom;

	@JsonProperty(required = true)
	private String prenom;

}
