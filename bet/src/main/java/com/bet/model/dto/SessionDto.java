package com.bet.model.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class SessionDto {

	private int id;
	private String nomSession;
	private Date dateCreationSession;

}
