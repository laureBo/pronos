package com.bet.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bet.model.dto.UtilisateurDto;
import com.bet.model.entity.UtilisateurEntity;
import com.bet.model.mapper.UtilisateurMapper;
import com.bet.model.repository.IUtilisateurRepository;

@Service
public class UtilisateurService {
	private static Logger logger = LoggerFactory.getLogger(UtilisateurService.class);

	@Autowired
	private IUtilisateurRepository utilisateurRepo;

	@Autowired
	private UtilisateurMapper mapper;

	public UtilisateurDto getByPseudo(String pseudo) {
		UtilisateurEntity entity = utilisateurRepo.findByPseudoUser(pseudo);
		return entity != null ? mapper.getDtoFromEntity(entity) : null;
	}

	public List<UtilisateurDto> findAllUtilisateur() {
		List<UtilisateurEntity> listEntity = utilisateurRepo.findAllUtilisateur();
		return mapper.getDtosFromEntities(listEntity);
	}

	public UtilisateurEntity saveUtilisateurInBd(UtilisateurDto input) {
		UtilisateurEntity utilisateurEntity = mapper.getEntityFromDto(input);
		return utilisateurRepo.save(utilisateurEntity);
	}

	public UtilisateurEntity findUtilisateurEntityByPseudo(String pseudo) {
		// Optional<UtilisateurEntity> optUser =
		// Optional.ofNullable(utilisateurRepo.getReferenceById(pseudo));
		// if (optUser.isEmpty()) {
		// logger.error("Aucune utilisateur ne correspond à ce pseudo");
		// return null;
		// }
		return utilisateurRepo.getReferenceById(pseudo);

	}

}
