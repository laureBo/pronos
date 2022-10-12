package com.bet.model.dto;

import lombok.Data;

@Data
public class PariInputDto {

	private String pseudo;
	private int idMatch;
	private int pariEquipe1;
	private int pariEquipe2;
}
