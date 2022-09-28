package com.bet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.bet.model.dto.MatchDto;
import com.bet.model.entity.MatchEntity;
import com.bet.model.mapper.MatchMapper;
import com.bet.model.repository.IMatchRepository;

@Service
public class MatchService {

	@Autowired
	private IMatchRepository matchRepository;

	@Autowired
	private MatchMapper matchMapper;

	public MatchDto getByMatchId(int matchId) {
		MatchEntity entity = matchRepository.findMatchByIdMatch(matchId);
		return entity != null ? matchMapper.getDtoFromEntity(entity) : null;
	}

	public MatchEntity mettreAJourScoreMatch(int idMatch, int scoreEquipe1, int scoreEquipe2) {
		MatchEntity match = matchRepository.findMatchByIdMatch(idMatch);
		if (match == null) {
			throw new ResourceNotFoundException();
		}
		match.setScoreEquipe1(scoreEquipe1);
		match.setScoreEquipe2(scoreEquipe2);
		MatchEntity matchSaved = matchRepository.save(match);
		return matchSaved;
	}
}
