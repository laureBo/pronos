package com.bet.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bet.model.dto.SessionDto;
import com.bet.model.dto.SessionInputDto;
import com.bet.model.entity.ParticiperEntity;
import com.bet.model.entity.SessionEntity;
import com.bet.model.mapper.SessionMapper;
import com.bet.model.repository.ISessionRepository;

@Service
public class SessionService {

	private static Logger logger = LoggerFactory.getLogger(SessionService.class);

	@Autowired
	private ISessionRepository sessionRepository;

	@Autowired
	private SessionMapper sessionMapper;

	public List<String> getAllNomSessionByUserList(String pseudo) {
		return sessionRepository.findAllNomSessionByUser(pseudo);
	}

	public SessionEntity createSession(SessionInputDto sessionInputDto) {
		SessionEntity newSession = sessionMapper.createEntityFromDto(sessionInputDto);
		sessionRepository.save(newSession);

		return newSession;

	}

	public SessionDto findSessionById(int idSession) {
		Optional<SessionEntity> optEntity = sessionRepository.findById(idSession);
		if (optEntity.isEmpty()) {
			logger.error("Aucune session ne correspond à cet identifiant");
			return null;
		}
		return sessionMapper.createDtoFromEntity(optEntity.get());

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
				participationList.add(participant.getUtilisateur().getNomUser());
			}

			return participationList;
		}
		return null;
	}

}
