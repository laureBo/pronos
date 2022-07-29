package com.bet.model.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bet.model.dto.UtilisateurDto;
import com.bet.model.entity.UtilisateurEntity;

@Service
public class UtilisateurMapper {

	public UtilisateurEntity getEntityFromDto(UtilisateurDto dto) {
		if (dto == null) {
			return null;
		}
		UtilisateurEntity entity = new UtilisateurEntity();
		entity.setPseudoUser(dto.getPseudo());
		entity.setMailUser(dto.getMail());
		entity.setNomUser(dto.getNom());
		entity.setPrenomUser(dto.getPrenom());
		return entity;
	}

	public UtilisateurDto getDtoFromEntity(UtilisateurEntity entity) {
		if (entity == null) {
			return null;
		}
		UtilisateurDto dto = new UtilisateurDto();
		dto.setPseudo(entity.getPseudoUser());
		dto.setMail(entity.getMailUser());
		dto.setNom(entity.getNomUser());
		dto.setPrenom(entity.getPrenomUser());
		return dto;
	}

	public List<UtilisateurDto> getDtosFromEntities(List<UtilisateurEntity> listEntity) {
		List<UtilisateurDto> listDto = new ArrayList<>();
		for (UtilisateurEntity entity : listEntity) {
			if (entity != null) {
				listDto.add(getDtoFromEntity(entity));
			}
		}
		return listDto;
	}

	public List<UtilisateurEntity> getEntitiesFromDtos(List<UtilisateurDto> listDto) {
		List<UtilisateurEntity> listEntity = new ArrayList<>();
		for (UtilisateurDto dto : listDto) {
			if (dto != null) {
				listEntity.add(getEntityFromDto(dto));
			}
		}
		return listEntity;
	}
}
