package com.bet.model.dto;

import java.util.Date;

import lombok.Data;

@Data
public class MatchDto {

	private int id;
	private String equipe1;
	private String equipe2;
	private int scoreEquipe1;
	private int scoreEquipe2;
	private Date dateMatch;

}
