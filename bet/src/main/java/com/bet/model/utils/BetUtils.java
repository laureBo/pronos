package com.bet.model.utils;

public class BetUtils {

	public static ResultatEnum getResultatEnumFromScore(Integer scoreEquipe1, Integer scoreEquipe2) {
		if (scoreEquipe1 > scoreEquipe2) {
			return ResultatEnum.EQUIPE1_GAGNE;
		}
		if (scoreEquipe2 > scoreEquipe1) {
			return ResultatEnum.EQUIPE2_GAGNE;
		} else {
			return ResultatEnum.MATCH_NUL;
		}
	}

}
