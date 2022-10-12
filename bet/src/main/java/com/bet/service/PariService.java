package com.bet.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.bet.model.dto.PariDetailDto;
import com.bet.model.dto.PariInputDto;
import com.bet.model.entity.PariEntity;
import com.bet.model.entity.UtilisateurEntity;
import com.bet.model.mapper.PariMapper;
import com.bet.model.repository.IMatchRepository;
import com.bet.model.repository.IPariRepository;
import com.bet.model.repository.IUtilisateurRepository;

@Service
public class PariService {

	@Autowired
	private IPariRepository pariRepository;
	@Autowired
	private IUtilisateurRepository utilisateurRepository;
	@Autowired
	private IMatchRepository matchRepository;

	@Autowired
	private PariMapper mapperPari;

	public List<PariEntity> getAllByPseudo(String pseudo) {
		UtilisateurEntity utilisateurEntity = utilisateurRepository.findByPseudoUser(pseudo);
		return pariRepository.findAllByUtilisateur(utilisateurEntity);
	}

	public List<PariDetailDto> getAllByPseudoAndNameSession(String pseudo, String nomSession) {
		List<PariDetailDto> parisDetailDtoList = new ArrayList<>();
		List<PariEntity> parisEntityList = pariRepository.findAllBetsbyPseudoAndNomSession(pseudo, nomSession);
		for (PariEntity pari : parisEntityList) {
			parisDetailDtoList.add(mapperPari.getDtoFromEntity(pari));
		}
		return parisDetailDtoList;
	}

	public List<PariDetailDto> getAllByPseudoAndIdSession(int idSession, String pseudo) {
		List<PariDetailDto> parisDtoList = new ArrayList<>();
		List<PariEntity> parisEntityList = pariRepository.findAllBetsbyPseudoAndIdSession(idSession, pseudo);
		for (PariEntity pari : parisEntityList) {
			parisDtoList.add(mapperPari.getDtoFromEntity(pari));
		}
		return parisDtoList;
	}

	public List<PariDetailDto> getParisDetailsByUser(String pseudo) {
		List<PariDetailDto> parisDetailDtoList = new ArrayList<>();
		UtilisateurEntity utilisateurEntity = utilisateurRepository.findByPseudoUser(pseudo);
		List<PariEntity> pariEntityList = pariRepository.findAllByUtilisateur(utilisateurEntity);
		for (PariEntity pari : pariEntityList) {
			if (pari.getMatch().getScoreEquipe1() != null) {
				parisDetailDtoList.add(mapperPari.getDtoFromEntity(pari));
			}
		}
		return parisDetailDtoList;
	}

	public List<PariDetailDto> getThreeLastBetByPseudo1(String pseudo) {
		List<PariDetailDto> parisDetailDtoList = new ArrayList<>();
		List<PariEntity> pariEntityList = pariRepository.findAllBetsbyPseudo(pseudo);
		for (PariEntity pari : pariEntityList) {
			parisDetailDtoList.add(mapperPari.getDtoFromEntity(pari));
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
			parisDetailDtoList.add(mapperPari.getDtoFromEntity(pari));
			index++;
		}
		return parisDetailDtoList;

	}

	public PariEntity savePariDto(PariInputDto pariInputDto) {
		PariEntity pariEntity = new PariEntity();
		if (utilisateurRepository.findById(pariInputDto.getPseudo()) == null) {
			throw new ResourceNotFoundException();
		} else if (matchRepository.findMatchByIdMatch(pariInputDto.getIdMatch()) == null) {
			throw new ResourceNotFoundException();
		}
		pariEntity = pariRepository.save(mapperPari.getEntityFromDto(pariInputDto));
		return pariEntity;

	}
}