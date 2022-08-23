package com.bet.model.mapper;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bet.model.dto.SessionDto;
import com.bet.model.dto.SessionInputDto;
import com.bet.model.entity.SessionEntity;
import com.bet.service.UtilisateurService;

@Service
public class SessionMapper {

	@Autowired
	private UtilisateurService utilisateurService;

	public SessionEntity createEntityFromDto(SessionInputDto sessionInputDto) {
		SessionEntity entity = new SessionEntity();
		entity.setNomSession(sessionInputDto.getNomSession());
		long millis = System.currentTimeMillis();
		Date date = new Date(millis);
		entity.setDateCreationSession(date);
		entity.setCreateur(utilisateurService.findUtilisateurEntityByPseudo(sessionInputDto.getPseudo()));

		return entity;
	}

	public SessionDto createDtoFromEntity(SessionEntity sessionEntity) {
		SessionDto dto = new SessionDto();
		dto.setId(sessionEntity.getIdSession());
		dto.setNomSession(sessionEntity.getNomSession());
		dto.setDateCreationSession(sessionEntity.getDateCreationSession());
		dto.setPseudoCreateur(sessionEntity.getCreateur().getPseudoUser());

		return dto;
	}

}
