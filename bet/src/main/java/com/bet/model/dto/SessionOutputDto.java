package com.bet.model.dto;

import java.sql.Date;
import java.util.List;

import lombok.Data;

@Data
public class SessionOutputDto {

	private int id;
	private String nomSession;
	private Date dateCreationSession;
	private String pseudoCreateur;
	private List<MatchDto> matchs;
	private List<String> participants;
}
