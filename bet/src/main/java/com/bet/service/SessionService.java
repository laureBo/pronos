package com.bet.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.bet.model.dto.MatchDto;
import com.bet.model.dto.SessionCreationInputDto;
import com.bet.model.dto.SessionInputDto;
import com.bet.model.dto.SessionLightOutputDto;
import com.bet.model.dto.SessionOutputDto;
import com.bet.model.entity.MatchEntity;
import com.bet.model.entity.ParticiperEntity;
import com.bet.model.entity.SessionEntity;
import com.bet.model.entity.UtilisateurEntity;
import com.bet.model.mapper.MatchMapper;
import com.bet.model.mapper.SessionMapper;
import com.bet.model.repository.ISessionRepository;

@Service
public class SessionService {

	private static Logger logger = LoggerFactory.getLogger(SessionService.class);

	@Autowired
	private ISessionRepository sessionRepository;

	@Autowired
	private SessionMapper sessionMapper;

	@Autowired
	private UtilisateurService utilisateurService;

	@Autowired
	private MatchMapper matchMapper;

	public List<String> getAllNomSessionByUserList(String pseudo) {
		return sessionRepository.findAllNomSessionByUser(pseudo);
	}

	public SessionEntity createSession(SessionCreationInputDto sessionCreationInputDto) {
		UtilisateurEntity user = utilisateurService
				.findUtilisateurEntityByPseudo(sessionCreationInputDto.getPseudoCreateur());
		if (user == null) {
			logger.info("createSession : Votre pseudo n'existe pas.");
			throw new ResourceNotFoundException(
					"createSession : Votre pseudo n'existe pas. " + sessionCreationInputDto.getPseudoCreateur());
		}
		SessionEntity newSession = sessionMapper.getEntityFromNewDto(sessionCreationInputDto);
		sessionRepository.save(newSession);

		return newSession;

	}

	public SessionEntity updateSession(SessionInputDto sessionInputDto) {
		UtilisateurEntity user = utilisateurService.findUtilisateurEntityByPseudo(sessionInputDto.getPseudoCreateur());
		if (user == null) {
			logger.info("createSession : Votre pseudo n'existe pas.");
			throw new ResourceNotFoundException(
					"createSession : Votre pseudo n'existe pas. " + sessionInputDto.getPseudoCreateur());
		}

		Optional<SessionEntity> session = findSessionEntityById(sessionInputDto.getId());
		if (session.isEmpty()) {
			logger.info("updateSession : Votre session n'existe pas.");
			throw new ResourceNotFoundException(
					"updateSession : Votre session n'existe pas. " + sessionInputDto.getId());
		}
		SessionEntity sessionUpdated = session.get();
		sessionUpdated.setCreateur(user);
		sessionUpdated.setNomSession(sessionInputDto.getNomSession());

		return sessionRepository.save(sessionUpdated);

	}

	public SessionOutputDto findSessionById(int idSession) {
		Optional<SessionEntity> optEntity = sessionRepository.findById(idSession);
		if (optEntity.isEmpty()) {
			logger.error("Aucune session ne correspond à cet identifiant");
			return null;
		}
		return sessionMapper.getDtoFromEntity(optEntity.get());
	}

	public List<SessionOutputDto> findAllSessions() {
		List<SessionEntity> listEntity = sessionRepository.findAll();
		return sessionMapper.getDtosFromEntities(listEntity);
	}

	public Optional<SessionEntity> findSessionEntityById(int idSession) {
		Optional<SessionEntity> optEntity = sessionRepository.findById(idSession);
		if (optEntity.isEmpty()) {
			logger.error("Aucune session ne correspond à cet identifiant");
			return null;
		}
		return optEntity;

	}

	public List<String> findParticipationsList(int idSession) {
		List<ParticiperEntity> entityList = sessionRepository.findAllParticipantsBySession(idSession);
		if (entityList.size() != 0) {
			List<String> participationList = new ArrayList<>();
			for (ParticiperEntity participant : entityList) {
				participationList.add(participant.getParticiperId().getUtilisateur().getPseudoUser());
			}

			return participationList;
		}
		return null;
	}

	public SessionEntity ajouterMatchToSession(int idSession, MatchDto match) {
		Optional<SessionEntity> optEntity = sessionRepository.findById(idSession);
		if (optEntity.isEmpty()) {
			throw new ResourceNotFoundException(
					"ajouterMatchsToSession pas de session correspondante trouvee " + idSession);
		}

		SessionEntity sessionUpdated = optEntity.get();
		MatchEntity matchEntity = matchMapper.getEntityFromDto(match);
		matchEntity.setSession(sessionUpdated);
		sessionUpdated.getMatchs().add(matchEntity);
		return sessionRepository.save(sessionUpdated);
	}

	public void supprimerMatchToSession(int idSession, MatchDto match) {
		Optional<SessionEntity> optEntity = sessionRepository.findById(idSession);
		if (optEntity.isEmpty()) {
			throw new ResourceNotFoundException(
					"supprimerMatchsToSession pas de session correspondante trouvee " + idSession);
		}

		SessionEntity sessionUpdated = optEntity.get();
		MatchEntity matchEntity = matchMapper.getEntityFromDto(match);
		matchEntity.setSession(sessionUpdated);
		sessionUpdated.getMatchs().add(matchEntity);
		sessionRepository.delete(sessionUpdated);
	}

	public List<SessionLightOutputDto> getAllSessionsByPseudo(String pseudo) {
		List<SessionEntity> sessionsEntitiesList = sessionRepository.findAllSessionsByUser(pseudo);
		return sessionMapper.getDtosLightFromEntities(sessionsEntitiesList);
	}

}
