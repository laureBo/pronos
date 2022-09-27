package com.bet.model.mapper;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bet.model.dto.SessionInputDto;
import com.bet.model.dto.SessionOutputDto;
import com.bet.model.entity.SessionEntity;
import com.bet.service.UtilisateurService;

@Service
public class SessionMapper {

	@Autowired
	private UtilisateurService utilisateurService;

	@Autowired
	private MatchMapper matchMapper;

	public SessionEntity getEntityFromDto(SessionInputDto sessionInputDto) {
		SessionEntity entity = new SessionEntity();
		entity.setNomSession(sessionInputDto.getNomSession());
		long millis = System.currentTimeMillis();
		Date date = new Date(millis);
		entity.setDateCreationSession(date);
		entity.setCreateur(utilisateurService.findUtilisateurEntityByPseudo(sessionInputDto.getPseudoCreateur()));
		return entity;
	}

	public SessionOutputDto getDtoFromEntity(SessionEntity sessionEntity) {
		SessionOutputDto dto = new SessionOutputDto();
		dto.setId(sessionEntity.getIdSession());
		dto.setNomSession(sessionEntity.getNomSession());
		dto.setDateCreationSession(sessionEntity.getDateCreationSession());
		dto.setPseudoCreateur(sessionEntity.getCreateur().getPseudoUser());
		dto.setMatchs(matchMapper.getDtosFromEntities(sessionEntity.getMatchs()));
		dto.setParticipants(sessionEntity.getParticipations().stream()
				.map((participation -> participation.getParticiperId().getUtilisateur().getPseudoUser()))
				.collect(Collectors.toList()));
		return dto;
	}

	public List<SessionOutputDto> getDtosFromEntities(List<SessionEntity> listEntity) {
		List<SessionOutputDto> listDto = new ArrayList<>();
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
