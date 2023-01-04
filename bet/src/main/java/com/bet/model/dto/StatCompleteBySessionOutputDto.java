package com.bet.model.dto;

import lombok.Data;

@Data
public class StatCompleteBySessionOutputDto implements Comparable<StatCompleteBySessionOutputDto> {
	private String pseudo;
	private Integer classement = null;
	private Integer scoreBySession;
	private Integer scoreTotalMaxSession;
	private Integer nbMatchsTrouves;
	private Integer nbMatchsExacts;
	private Integer nbMatchsSession;

	@Override
	public int compareTo(StatCompleteBySessionOutputDto stat) {
		// trions les stats selon leur scoreBySession dans l'ordre croissant
		// retourne un entier négative, zéro ou positive si scoreBySession
		// de cet stat est moins que, égale à ou supérieur à l'objet comparé avec
		return (stat.scoreBySession - this.scoreBySession);
	}
}
