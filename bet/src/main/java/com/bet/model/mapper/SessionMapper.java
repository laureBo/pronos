package com.bet.model.mapper;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

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

	public SessionEntity getEntityFromDto(SessionInputDto sessionInputDto) {
		SessionEntity entity = new SessionEntity();
		entity.setNomSession(sessionInputDto.getNomSession());
		long millis = System.currentTimeMillis();
		Date date = new Date(millis);
		entity.setDateCreationSession(date);
		entity.setCreateur(utilisateurService.findUtilisateurEntityByPseudo(sessionInputDto.getPseudo()));

		return entity;
	}

	public SessionDto getDtoFromEntity(SessionEntity sessionEntity) {
		SessionDto dto = new SessionDto();
		dto.setId(sessionEntity.getIdSession());
		dto.setNomSession(sessionEntity.getNomSession());
		dto.setDateCreationSession(sessionEntity.getDateCreationSession());
		dto.setPseudoCreateur(sessionEntity.getCreateur().getPseudoUser());

		return dto;
	}

	public List<SessionDto> getDtosFromEntities(List<SessionEntity> listEntity) {
		List<SessionDto> listDto = new ArrayList<>();
		for (SessionEntity entity : listEntity) {
			if (entity != null) {
				listDto.add(getDtoFromEntity(entity));
			}
		}
		return listDto;
	}

	public List<SessionEntity> getEntitiesFromDtos(List<SessionInputDto> listDto) {
		List<SessionEntity> listEntity = new ArrayList<>();
		for (SessionInputDto dto : listDto) {
			if (dto != null) {
				listEntity.add(getEntityFromDto(dto));
			}
		}
		return listEntity;
	}

}
