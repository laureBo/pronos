package com.bet.model.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bet.model.dto.MatchDto;
import com.bet.model.entity.MatchEntity;

@Service
public class MatchMapper {

	public MatchEntity getEntityFromDto(MatchDto dto) {
		MatchEntity entity = new MatchEntity();
		if (dto.getId() != null) {
			entity.setIdMatch(dto.getId());
		}
		entity.setEquipe1(dto.getEquipe1());
		entity.setEquipe2(dto.getEquipe2());
		entity.setScoreEquipe1(dto.getScoreEquipe1());
		entity.setScoreEquipe2(dto.getScoreEquipe2());
		entity.setDateMatch(new java.sql.Date(dto.getDateMatch().getTime()));
		return entity;
	}

	public MatchDto getDtoFromEntity(MatchEntity entity) {
		MatchDto dto = new MatchDto();
		dto.setId(entity.getIdMatch());
		dto.setEquipe1(entity.getEquipe1());
		dto.setEquipe2(entity.getEquipe2());
		dto.setScoreEquipe1(entity.getScoreEquipe1());
		dto.setScoreEquipe2(entity.getScoreEquipe2());
		dto.setDateMatch(entity.getDateMatch());

		return dto;
	}

	public List<MatchDto> getDtosFromEntities(List<MatchEntity> listEntity) {
		List<MatchDto> listDto = new ArrayList<>();
		for (MatchEntity entity : listEntity) {
			if (entity != null) {
				listDto.add(getDtoFromEntity(entity));
			}
		}
		return listDto;
	}

	public List<MatchEntity> getEntitiesFromDtos(List<MatchDto> listDto) {
		List<MatchEntity> listEntity = new ArrayList<>();
		for (MatchDto dto : listDto) {
			if (dto != null) {
				listEntity.add(getEntityFromDto(dto));
			}
		}
		return listEntity;
	}

}
