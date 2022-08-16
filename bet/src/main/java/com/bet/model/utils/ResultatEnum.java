package com.bet.model.utils;

public enum ResultatEnum {

	EQUIPE1_GAGNE("equipe 1 gagne"), EQUIPE2_GAGNE("equipe 2 gagne"), MATCH_NUL("égalité");

	private String label;

	private ResultatEnum(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}
}
