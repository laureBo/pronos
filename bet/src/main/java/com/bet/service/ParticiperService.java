package com.bet.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bet.model.dto.ParticiperInputDto;
import com.bet.model.entity.ParticiperEntity;
import com.bet.model.entity.SessionEntity;
import com.bet.model.entity.UtilisateurEntity;
import com.bet.model.repository.IParticiperRepository;

@Service
public class ParticiperService {

	private static Logger logger = LoggerFactory.getLogger(ParticiperService.class);

	@Autowired
	private IParticiperRepository participerRepository;

	@Autowired
	private UtilisateurService utilisateurService;

	@Autowired
	private SessionService sessionService;

	public ParticiperEntity associateParticipantToSession(ParticiperInputDto participerInputDto) {
		UtilisateurEntity user = utilisateurService.findUtilisateurEntityByPseudo(participerInputDto.getPseudo());

		Optional<SessionEntity> session = sessionService.findSessionEntityById(participerInputDto.getIdSession());

		if (user == null) {
			logger.info("Votre pseudo n'existe pas.");
			return null;
		}
		if (session == null) {
			logger.info("Votre session n'existe pas.");
			return null;
		} else {
			ParticiperEntity participation = participerRepository.saveParticipant(user.getPseudoUser(),
					session.get().getIdSession());

			return participation;
		}
	}
}
