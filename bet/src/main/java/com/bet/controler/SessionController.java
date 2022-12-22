package com.bet.controler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bet.model.dto.InfoReturn;
import com.bet.model.dto.MatchDto;
import com.bet.model.dto.PariDetailDto;
import com.bet.model.dto.SessionCreationInputDto;
import com.bet.model.dto.SessionInputDto;
import com.bet.model.dto.SessionOutputDto;
import com.bet.model.dto.UserSessionInputDto;
import com.bet.model.entity.SessionEntity;
import com.bet.service.PariService;
import com.bet.service.ParticiperService;
import com.bet.service.SessionService;
import com.bet.service.StatService;
import com.bet.service.UtilisateurService;

@RestController
@RequestMapping(value = "/sessions")
public class SessionController {

	private static Logger logger = LoggerFactory.getLogger(PariController.class);

	@Autowired
	private SessionService sessionService;

	@Autowired
	private ParticiperService participerService;

	@Autowired
	private PariService pariService;

	@Autowired
	private StatService statService;

	@Autowired
	private UtilisateurService utilisateurService;

	/**
	 * Returns the session from the session's identifier
	 *
	 * @param idSession session identifier
	 * @return the session linked to the identifier
	 */
	@GetMapping(value = "/{idSession}")
	public ResponseEntity<SessionOutputDto> findSessionById(@PathVariable final int idSession) {
		logger.info("Find session by id: findSessionById");
		// Search session on database
		final SessionOutputDto resultDto = this.sessionService.findSessionById(idSession);
		// If the session does exist on database then return it as response entity
		if (resultDto != null) {
			return new ResponseEntity<>(resultDto, HttpStatus.OK);
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
	public List<SessionOutputDto> getAllSessions() {
		logger.info("Get all sessions: getAllSessions");
		return this.sessionService.findAllSessions();
	}

	/**
	 * Create a new session
	 *
	 * @param input a new session dto to create
	 * @return the url to access to the created session
	 */
	@PostMapping(value = "/", consumes = "application/json", produces = "application/json")
	public ResponseEntity<InfoReturn> createSession(@RequestBody final SessionCreationInputDto input) {
		logger.info("Creation session: createSession");
		// Create session on database
		final SessionEntity resultEntity = this.sessionService.createSession(input);
		// Return created object as response entity
		participerService.associateParticipantToSession(resultEntity.getIdSession(), input.getPseudoCreateur());
		return new ResponseEntity<>(new InfoReturn("sessions/" + resultEntity.getIdSession()), HttpStatus.CREATED);
	}

	@PutMapping(value = "/")
	public ResponseEntity<String> updateSession(@RequestBody final SessionInputDto input) {
		logger.info("Update session: updateSession");
		// Create session on database
		final SessionEntity resultEntity = this.sessionService.updateSession(input);
		// Return created object as response entity
		return new ResponseEntity<>("/sessions/" + resultEntity.getIdSession(), HttpStatus.CREATED);
	}

	/**
	 * Get the list of pseudo of the users participating to a given session
	 * identifier
	 *
	 * @param idSession session identifier
	 * @return the list of pseudo participating to the session
	 */
	@GetMapping(value = "/{idSession}/utilisateurs")
	public ResponseEntity<List<String>> findAllUtilisateurBySession(@PathVariable final int idSession) {
		logger.info("Find utilisateur by id session: findAllUtilisateurBySession");
		// Search for users linked to the session in database
		final List<String> participantsList = this.sessionService.findParticipationsList(idSession);
		// Return created list as response entity
		return new ResponseEntity<>(participantsList, HttpStatus.OK);
	}

	/**
	 * Add a user to a session
	 *
	 * @param input the users's pseudo and the session name
	 * @return the url to reach the session's users
	 */
	@PostMapping(value = "/ajouter-participant/")
	public ResponseEntity<String> addUtilisateurToSession(@RequestBody final UserSessionInputDto input) {
		logger.info("ajouter-participant");
		this.participerService.associateParticipantToSession(input.getIdSession(), input.getPseudo());
		return new ResponseEntity<>("/sessions/" + input.getIdSession() + "/utilisateurs", HttpStatus.CREATED);
	}

	@PostMapping(value = "/{idSession}/ajouter-match")
	public ResponseEntity<String> ajouterMatchASession(@PathVariable final int idSession,
			@RequestBody final MatchDto matchDto) {
		logger.info("ajouterMatchASession");
		this.sessionService.ajouterMatchToSession(idSession, matchDto);
		return new ResponseEntity<>("/sessions/" + idSession, HttpStatus.CREATED);
	}

	@GetMapping(value = "/{idSession}/utilisateur/{pseudo}/paris")
	public List<PariDetailDto> getAllBetsByUserSession(@PathVariable final int idSession,
			@PathVariable final String pseudo) {
		logger.info("Get all bets to user's session: getAllBetsByUserSession");
		return this.pariService.getAllByPseudoAndIdSession(idSession, pseudo);

	}

	@GetMapping(value = "/{idSession}/utilisateur/{pseudo}/paris/score")
	public int getScorePourcentUserBySessionAndPseudo(@PathVariable final int idSession,
			@PathVariable final String pseudo) {
		return this.statService.calculateScoreUserBySessionAndpseudo(idSession, pseudo);

	}

	@GetMapping(value = "/{idSession}/utilisateurs/ranking")
	public Map<String, Integer> getUsersRankingBySession(@PathVariable final int idSession) {
		Map<String, Integer> scoreUsersMap = new HashMap<>();
		scoreUsersMap = this.statService.getRankingPlayersBySession(idSession);

		return scoreUsersMap;

	}
}
