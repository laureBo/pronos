package com.bet.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bet.model.dto.PariDetailDto;
import com.bet.model.entity.PariEntity;
import com.bet.model.entity.UtilisateurEntity;
import com.bet.model.mapper.PariMapper;
import com.bet.model.repository.IPariRepository;
import com.bet.model.repository.IUtilisateurRepository;

@Service
public class PariService {

	@Autowired
	private IPariRepository pariRepository;
	@Autowired
	private IUtilisateurRepository utilisateurRepository;

	@Autowired
	private PariMapper mapperPariMatch;

	public List<PariEntity> getAllByPseudo(String pseudo) {
		UtilisateurEntity utilisateurEntity = utilisateurRepository.findByPseudoUser(pseudo);
		return pariRepository.findAllByUtilisateur(utilisateurEntity);
	}

	public List<PariDetailDto> getAllByPseudoAndNameSession(String pseudo, String nomSession) {
		List<PariDetailDto> parisDetailDtoList = new ArrayList<>();
		List<PariEntity> parisEntityList = pariRepository.findAllBetsbyPseudoAndNomSession(pseudo, nomSession);
		for (PariEntity pari : parisEntityList) {
			parisDetailDtoList.add(mapperPariMatch.getDtoFromEntity(pari));
		}
		return parisDetailDtoList;
	}

	public List<PariDetailDto> getParisDetailsByUser(String pseudo) {
		List<PariDetailDto> parisDetailDtoList = new ArrayList<>();
		UtilisateurEntity utilisateurEntity = utilisateurRepository.findByPseudoUser(pseudo);
		List<PariEntity> pariEntityList = pariRepository.findAllByUtilisateur(utilisateurEntity);
		for (PariEntity pari : pariEntityList) {
			if (pari.getMatch().getScoreEquipe1() != null) {
				parisDetailDtoList.add(mapperPariMatch.getDtoFromEntity(pari));
			}
		}
		return parisDetailDtoList;
	}

	public List<PariDetailDto> getThreeLastBetByPseudo1(String pseudo) {
		List<PariDetailDto> parisDetailDtoList = new ArrayList<>();
		List<PariEntity> pariEntityList = pariRepository.findAllBetsbyPseudo(pseudo);
		for (PariEntity pari : pariEntityList) {
			parisDetailDtoList.add(mapperPariMatch.getDtoFromEntity(pari));
			if (parisDetailDtoList.size() == 2) {

				break;
			}
		}
		return parisDetailDtoList;

	}

	public List<PariDetailDto> getThreeLastBetByPseudo2(String pseudo) {
		List<PariDetailDto> parisDetailDtoList = new ArrayList<>();
		List<PariEntity> pariEntityList = pariRepository.findAllBetsbyPseudo(pseudo);
		int index = 0;
		while (index < 2 && index < pariEntityList.size()) {
			PariEntity pari = pariEntityList.get(index);
			parisDetailDtoList.add(mapperPariMatch.getDtoFromEntity(pari));
			index++;
		}
		return parisDetailDtoList;

	}

}