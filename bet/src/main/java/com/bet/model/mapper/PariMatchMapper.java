package com.bet.model.mapper;

import org.springframework.stereotype.Service;

import com.bet.model.dto.PariDetailDto;
import com.bet.model.entity.PariEntity;
import com.bet.model.utils.BetUtils;
import com.bet.model.utils.ResultatEnum;

@Service
public class PariMatchMapper {

	public PariDetailDto getDtoFromEntity(PariEntity entity) {
		if (entity == null) {
			return null;
		}
		PariDetailDto pariDetailDto = new PariDetailDto();
		pariDetailDto.setEquipe1(entity.getMatch().getEquipe1());
		pariDetailDto.setPariEquipe1(entity.getEquipe1());
		pariDetailDto.setEquipe2(entity.getMatch().getEquipe2());
		pariDetailDto.setPariEquipe2(entity.getEquipe2());
		pariDetailDto.setScoreEquipe1(entity.getMatch().getScoreEquipe1());
		pariDetailDto.setScoreEquipe2(entity.getMatch().getScoreEquipe2());
		pariDetailDto.setDateMatch(entity.getMatch().getDateMatch());
		ResultatEnum resultatPari = BetUtils.getResultatEnumFromScore(entity.getEquipe1(), entity.getEquipe2());
		ResultatEnum resultatMatch = BetUtils.getResultatEnumFromScore(entity.getMatch().getScoreEquipe1(),
				entity.getMatch().getScoreEquipe2());
		if (resultatPari == resultatMatch) {
			pariDetailDto.setIsWinner(true);
		} else {
			pariDetailDto.setIsWinner(false);
		}
		if (entity.getEquipe1() == entity.getMatch().getScoreEquipe1()
				&& entity.getEquipe2() == entity.getMatch().getScoreEquipe2()) {
			pariDetailDto.setIsPerfect(true);
		} else {
			pariDetailDto.setIsPerfect(false);
		}
		return pariDetailDto;
	}

}
