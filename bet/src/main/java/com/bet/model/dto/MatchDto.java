package com.bet.model.dto;

import java.util.Date;

import lombok.Data;

@Data
public class MatchDto {

	private Integer id;
	private String equipe1;
	private String equipe2;
	private Integer scoreEquipe1;
	private Integer scoreEquipe2;
	private Date dateMatch;

}
