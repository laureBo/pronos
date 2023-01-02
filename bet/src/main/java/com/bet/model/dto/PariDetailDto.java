package com.bet.model.dto;

import java.util.Date;

import lombok.Data;

@Data
public class PariDetailDto {

	private Integer idMatch;
	private String equipe1;
	private Integer pariEquipe1;
	private String equipe2;
	private Integer pariEquipe2;
	private Integer scoreEquipe1;
	private Integer scoreEquipe2;
	private Date dateMatch;
	private Boolean isWinner;
	private Boolean isPerfect;

}
