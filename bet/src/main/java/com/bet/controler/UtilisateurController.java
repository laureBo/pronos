package com.bet.controler;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.bet.model.dto.InfoReturn;
import com.bet.model.dto.PariDetailDto;
import com.bet.model.dto.SessionLightOutputDto;
import com.bet.model.dto.UtilisateurDto;
import com.bet.service.PariService;
import com.bet.service.SessionService;
import com.bet.service.UtilisateurService;

@RestController
@RequestMapping(value = "/utilisateurs")
public class UtilisateurController {

	private static Logger logger = LoggerFactory.getLogger(UtilisateurController.class);

	@Autowired
	private UtilisateurService utilisateurService;

	@Autowired
	private PariService pariService;
	@Autowired
	private SessionService sessionService;

	@GetMapping(value = "/")
	public List<UtilisateurDto> getAllUtilisateur() {
		logger.info("Get all utilisateur: getAllUtilisateur");
		return utilisateurService.findAllUtilisateur();
	}

	/**
	 * Return the user linked to the pseudo
	 * 
	 * @param pseudo user's pseudo
	 * @return the user with all informations
	 */
	@GetMapping(value = "/{pseudo}")
	public UtilisateurDto getUtilisateurByPseudo(@PathVariable String pseudo) {
		logger.info("Get utilisateur by pseudo: getUtilisateurByPseudo");
		UtilisateurDto result = utilisateurService.getByPseudo(pseudo);
		if (result == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found");
		}
		return result;
	}

	/**
	 * Create a new user
	 * 
	 * @param input user input to create
	 * @return the url to reach the created user trough the api
	 */
	@PostMapping(value = "/")
	public ResponseEntity<InfoReturn> saveNewUtilisateur(@RequestBody UtilisateurDto input) {
		logger.info("saveNewUtilisateur");
		utilisateurService.saveUtilisateurInBd(input);
		return new ResponseEntity<InfoReturn>(new InfoReturn("/utilisateurs/" + input.getPseudo()), HttpStatus.CREATED);
	}

	/**
	 * List the bets linked to a user
	 * 
	 * @param pseudo user's pseudo
	 * @return bets details fir the user
	 */
	@GetMapping(value = "/{pseudo}/paris")
	public List<PariDetailDto> getParisDetailByUser(@PathVariable String pseudo) {
		logger.info("getParisDetailByUser");
		return pariService.getThreeLastBetByPseudo2(pseudo);
	}

	/**
	 * List the sessions linked to a user
	 * 
	 * @param pseudo user's pseudo
	 * @return sessions details for the user
	 */
	@GetMapping(value = "/{pseudo}/sessions")
	public List<SessionLightOutputDto> getSessionsDetailByUser(@PathVariable String pseudo) {
		logger.info("getSessionsDetailByUser");
		return sessionService.getAllSessionsByPseudo(pseudo);
	}
}
