package com.bet.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.bet.model.entity.ParticiperEntity;
import com.bet.model.entity.ParticiperIdEntity;
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

	public ParticiperEntity associateParticipantToSession(int idSession, String pseudo) {
		UtilisateurEntity user = utilisateurService.findUtilisateurEntityByPseudo(pseudo);
		if (user == null) {
			logger.info("associateParticipantToSession : Votre pseudo n'existe pas.");
			throw new ResourceNotFoundException("associateParticipantToSession : Votre pseudo n'existe pas. " + pseudo);
		}
		Optional<SessionEntity> session = sessionService.findSessionEntityById(idSession);
		if (!session.isPresent()) {
			logger.info("associateParticipantToSession : Votre session n'existe pas.");
			throw new ResourceNotFoundException(
					"associateParticipantToSession : Votre session n'existe pas. " + idSession);
		}

		ParticiperEntity participation = new ParticiperEntity();
		ParticiperIdEntity participerIdEntity = new ParticiperIdEntity();

		participerIdEntity.setSession(session.get());
		participerIdEntity.setUtilisateur(user);
		participation.setParticiperId(participerIdEntity);
		return participerRepository.save(participation);
	}
}
