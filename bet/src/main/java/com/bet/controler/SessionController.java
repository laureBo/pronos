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

import com.bet.model.dto.SessionDto;
import com.bet.model.dto.SessionInputDto;
import com.bet.model.entity.SessionEntity;
import com.bet.service.SessionService;

@RestController
@RequestMapping(value = "/sessions")
public class SessionController {

	private static Logger logger = LoggerFactory.getLogger(PariController.class);

	@Autowired
	private SessionService sessionService;

	@GetMapping(value = "/{idSession}")
	public ResponseEntity<SessionDto> findSessionById(@PathVariable int idSession) {
		logger.info("Find session by id: findSessionById");
		// Search session on database
		SessionDto resultDto = sessionService.findSessionById(idSession);
		// If the session does exist on database then return it as response entity
		if (resultDto != null) {
			return new ResponseEntity<SessionDto>(resultDto, HttpStatus.OK);
		}
		// If the session does not exist, then return not found response entity
		logger.info("Session not found " + idSession);
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@GetMapping(value = "/")
	public List<SessionDto> getAllSessions() {
		logger.info("Get all sessions: getAllSessions");
		return sessionService.findAllSessions();
	}

	@PostMapping(value = "/")
	public ResponseEntity<String> createSession(@RequestBody SessionInputDto input) {
		logger.info("Creation session: createSession");
		// Create session on database
		SessionEntity resultEntity = sessionService.createSession(input);
		// Return created object as response entity
		return new ResponseEntity<String>("/session/" + resultEntity.getIdSession(), HttpStatus.CREATED);
	}

	@GetMapping(value = "/{idSession}/utilisateurs")
	public ResponseEntity<List<String>> findAllUtilisateurBySession(@PathVariable int idSession) {
		logger.info("Find utilisateur by id session: findAllUtilisateurBySession");
		// Search for users linked to the session in database
		List<String> participantsList = sessionService.findParticipationsList(idSession);
		// Return created list as response entity
		return new ResponseEntity<List<String>>(participantsList, HttpStatus.OK);
	}
}
