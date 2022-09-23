package com.bet.controler;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bet.model.dto.PariDetailDto;
import com.bet.model.dto.StatByUserSessionInputDto;
import com.bet.model.dto.StatByUserSessionOutputDto;
import com.bet.service.PariService;
import com.bet.service.StatService;

@RestController
@RequestMapping(value = "/stats")
public class StatController {

	private static Logger logger = LoggerFactory.getLogger(StatController.class);

	@Autowired
	private StatService statService;

	@Autowired
	private PariService pariService;

	@GetMapping(value = "/{pseudo}")
	public List<StatByUserSessionOutputDto> getListAvgBetsBySession(@PathVariable String pseudo) {
		logger.info("Info log message liste de moyennes par session de l'utilisateur");
		return statService.getAllUserStatByPseudoAndNomSession(pseudo);
	}

	@PostMapping(value = "/bySession")
	public StatByUserSessionOutputDto getStatBySession(@RequestBody StatByUserSessionInputDto input) {
		return statService.getUserStatByPseudoAndNomSession(input.getPseudo(), input.getNomSession());
	}

	@GetMapping(value = "/all/{pseudo}")
	public int getStatAllByPseudo(@PathVariable String pseudo) {
		List<PariDetailDto> listParisDetailDto = pariService.getParisDetailsByUser(pseudo);
		logger.info("Info log message !!");
		return statService.getAvgWinnerBet(listParisDetailDto);
	}
}
