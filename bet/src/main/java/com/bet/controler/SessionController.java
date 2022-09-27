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
import com.bet.model.dto.UserSessionInputDto;
import com.bet.model.entity.SessionEntity;
import com.bet.service.ParticiperService;
import com.bet.service.SessionService;

@RestController
@RequestMapping(value = "/sessions")
public class SessionController {

	private static Logger logger = LoggerFactory.getLogger(PariController.class);

	@Autowired
	private SessionService sessionService;

	@Autowired
	private ParticiperService participerService;

	/**
	 * Returns the session from the session's identifier
	 * 
	 * @param idSession session identifier
	 * @return the session linked to the identifier
	 */
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

	/**
	 * Returns all the sessions
	 * 
	 * @return all sessions available in the database
	 */
	@GetMapping(value = "/")
	public List<SessionDto> getAllSessions() {
		logger.info("Get all sessions: getAllSessions");
		return sessionService.findAllSessions();
	}

	/**
	 * Create a new session
	 * 
	 * @param input a new session dto to create
	 * @return the url to access to the created session
	 */
	@PostMapping(value = "/")
	public ResponseEntity<String> createSession(@RequestBody SessionInputDto input) {
		logger.info("Creation session: createSession");
		// Create session on database
		SessionEntity resultEntity = sessionService.createSession(input);
		// Return created object as response entity
		return new ResponseEntity<String>("/session/" + resultEntity.getIdSession(), HttpStatus.CREATED);
	}

	/**
	 * Get the list of pseudo of the users participating to a given session
	 * identifier
	 * 
	 * @param idSession session identifier
	 * @return the list of pseudo participating to the session
	 */
	@GetMapping(value = "/{idSession}/utilisateurs")
	public ResponseEntity<List<String>> findAllUtilisateurBySession(@PathVariable int idSession) {
		logger.info("Find utilisateur by id session: findAllUtilisateurBySession");
		// Search for users linked to the session in database
		List<String> participantsList = sessionService.findParticipationsList(idSession);
		// Return created list as response entity
		return new ResponseEntity<List<String>>(participantsList, HttpStatus.OK);
	}

	/**
	 * Add a user to a session
	 * 
	 * @param input the users's pseudo and the session name
	 * @return the url to reach the session
	 */
	@PostMapping(value = "/addUserToSession")
	public ResponseEntity<String> addUserToSession(@RequestBody UserSessionInputDto input) {
		participerService.associateParticipantToSession(input.getIdSession(), input.getPseudo());
		// Return created object as response entity
		return new ResponseEntity<String>("/session/" + input.getIdSession(), HttpStatus.CREATED);
	}

}
