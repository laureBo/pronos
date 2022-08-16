package com.bet.service;

import java.util.ArrayList;
import java.util.List;

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
}
