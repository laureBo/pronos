package com.bet.model.dto;

import lombok.Data;

@Data
public class PariInputDto {
	private int idMatch;
	private String pseudo;
	private int pariEquipe1;
	private int pariEquipe2;
}
