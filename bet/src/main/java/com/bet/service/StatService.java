package com.bet.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bet.model.dto.PariDetailDto;
import com.bet.model.dto.StatByUserSessionOutputDto;

@Service
public class StatService {
	private static Logger logger = LoggerFactory.getLogger(StatService.class);

	@Autowired
	private PariService pariService;
	@Autowired
	private SessionService sessionService;

	public static int resultWinner = 2;

	public static int resultPerfect = 3;

	public int getAvgWinnerBet(List<PariDetailDto> listParisDetailDto) {
		float nbMatchTrouve = 0;
		Float avg = 0f;
		float totalBets = listParisDetailDto.size();
		for (PariDetailDto pari : listParisDetailDto) {
			if (pari.getIsWinner()) {
				nbMatchTrouve++;
			}
		}
		if (totalBets != 0) {
			avg = (nbMatchTrouve / totalBets) * 100;
		}
		logger.info("La moyenne est de " + avg);
		return avg.intValue();
	}

	public StatByUserSessionOutputDto getUserStatByPseudoAndNomSession(String pseudo, String nomSession) {
		List<PariDetailDto> listParisDetailDto = pariService.getAllByPseudoAndNameSession(pseudo, nomSession);
		logger.info("Info log message moyenne de l'utilisateur pour une session");
		StatByUserSessionOutputDto output = new StatByUserSessionOutputDto();
		output.setPourcentage(getAvgWinnerBet(listParisDetailDto));
		output.setNomSession(nomSession);
		return output;
	}

	public List<StatByUserSessionOutputDto> getAllUserStatByPseudoAndNomSession(String pseudo) {
		List<StatByUserSessionOutputDto> resultList = new ArrayList<>();
		List<String> nomSessionList = sessionService.getAllNomSessionByUserList(pseudo);
		for (String nomSession : nomSessionList) {
			resultList.add(getUserStatByPseudoAndNomSession(pseudo, nomSession));
		}
		return resultList;

	}

	public int calculateScoreUserBySessionAndpseudo(int idSession, String pseudo) {
		float scoreUser = 0;
		float scoreUserTotal = 0;
		Float pourcentScore;
		List<PariDetailDto> listeParis = new ArrayList<>();
		listeParis = pariService.getAllByPseudoAndIdSession(idSession, pseudo);
		int nbMatchsJoues = listeParis.size();
		for (PariDetailDto pariJoue : listeParis) {
			if (pariJoue.getIsWinner() == true && pariJoue.getIsPerfect() == true) {
				scoreUser = resultPerfect;
			} else if (pariJoue.getIsWinner() && pariJoue.getIsPerfect() == false) {
				scoreUser = resultWinner;
			} else {
				scoreUser = 0;
			}
			scoreUserTotal = scoreUserTotal + scoreUser;
		}
		pourcentScore = (scoreUserTotal / (nbMatchsJoues * resultPerfect)) * 100;
		return pourcentScore.intValue();
	}

	public Map<String, Integer> getRankingPlayersBySession(int idSession) {
		List<String> participantsList = new ArrayList<>();
		Map<String, Integer> scoreUsersMap = new HashMap<>();
		participantsList = sessionService.findParticipationsList(idSession);
		for (String user : participantsList) {
			int pourcentUser = calculateScoreUserBySessionAndpseudo(idSession, user);
			scoreUsersMap.put(user, pourcentUser);

		}
		Map<String, Integer> sorted = scoreUsersMap.entrySet().stream()
				.sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));

		return sorted;
	}
}
