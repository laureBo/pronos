package com.bet.model.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class SessionLightOutputDto {

	private int id;
	private String nomSession;
	private Date dateCreationSession;
	private String pseudoCreateur;

}
