package com.bet.controler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bet.model.dto.InfoReturn;
import com.bet.model.dto.MajScoreInputDto;
import com.bet.model.dto.MatchDto;
import com.bet.service.MatchService;

@RestController
@RequestMapping(value = "/matchs")
public class MatchController {
	private static Logger logger = LoggerFactory.getLogger(MatchController.class);

	@Autowired
	private MatchService matchService;

	@GetMapping(value = "/{idMatch}")
	public MatchDto getMatchByIdMatch(@PathVariable int idMatch) {
		logger.info("Get match by identifier: getMatchByIdMatch");
		return matchService.getByMatchId(idMatch);
	}

	@PutMapping(value = "/{idMatch}/maj-score")
	public ResponseEntity<InfoReturn> mettreAJourScoreMatch(@PathVariable int idMatch,
			@RequestBody MajScoreInputDto input) {
		logger.info("mettre A Jour Score Match: mettreAJourScoreMatch");
		matchService.mettreAJourScoreMatch(idMatch, input.getScoreEquipe1(), input.getScoreEquipe2());
		return new ResponseEntity<>(new InfoReturn("/matchs/" + idMatch), HttpStatus.ACCEPTED);
	}

	@DeleteMapping(value = "/{idMatch}")
	public ResponseEntity<InfoReturn> supprimerMatchASession(@PathVariable final int idMatch) {
		logger.info("supprimerMatchASession pour " + idMatch);
		this.matchService.supprimerMatchFromIdMatch(idMatch);
		return new ResponseEntity<InfoReturn>(new InfoReturn("deleted"), HttpStatus.OK);
	}

}
