package com.bet.model.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bet.model.dto.PariDetailDto;
import com.bet.model.dto.PariInputDto;
import com.bet.model.dto.PariOutputDto;
import com.bet.model.entity.PariEntity;
import com.bet.model.entity.UtilisateurEntity;
import com.bet.model.repository.IMatchRepository;
import com.bet.model.utils.BetUtils;
import com.bet.model.utils.ResultatEnum;
import com.bet.service.UtilisateurService;

@Service
public class PariMapper {

	@Autowired
	private UtilisateurService utilisateurService;

	@Autowired
	private IMatchRepository matchRepository;

//ParisDetailDto
	public PariDetailDto getDtoFromEntity(PariEntity entity) {
		if (entity == null) {
			return null;
		}
		PariDetailDto pariDetailDto = new PariDetailDto();
		pariDetailDto.setIdMatch(entity.getMatch().getIdMatch());
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

	// ParisOutputDto
	public PariOutputDto getOutputDtoFromEntity(PariEntity entity) {
		if (entity == null) {
			return null;
		}
		PariOutputDto pariOutputDto = new PariOutputDto();
		pariOutputDto.setIdMatch(entity.getMatch().getIdMatch());
		pariOutputDto.setPariEquipe1(entity.getEquipe1());
		pariOutputDto.setPariEquipe2(entity.getEquipe2());
		return pariOutputDto;
	}

	public PariEntity getEntityFromDto(PariInputDto pariInputDto) {
		if (pariInputDto == null) {
			return null;
		}
		PariEntity pariEntity = new PariEntity();
		UtilisateurEntity user = new UtilisateurEntity();
		user = utilisateurService.findUtilisateurEntityByPseudo(pariInputDto.getPseudo());
		pariEntity.setUtilisateur(utilisateurService.findUtilisateurEntityByPseudo(pariInputDto.getPseudo()));
		pariEntity.setMatch(matchRepository.findMatchByIdMatch(pariInputDto.getIdMatch()));
		pariEntity.setEquipe1(pariInputDto.getPariEquipe1());
		pariEntity.setEquipe2(pariInputDto.getPariEquipe2());
		return pariEntity;
	}
}
