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

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "/session")
@Slf4j

public class SessionController {

	private static Logger logger = LoggerFactory.getLogger(PariController.class);

	@Autowired
	private SessionService sessionService;

	@PostMapping(value = "/create")
	public ResponseEntity<String> createSession(@RequestBody SessionInputDto input) {
		logger.info("Session créée");
		SessionEntity resultEntity = sessionService.createSession(input);
		return new ResponseEntity<String>("/session/" + resultEntity.getIdSession(), HttpStatus.CREATED);
	}

	@GetMapping(value = "/{idSession}")
	public ResponseEntity<SessionDto> findSessionById(@PathVariable int idSession) {
		SessionDto resultDto = sessionService.findSessionById(idSession);

		if (resultDto != null) {
			return new ResponseEntity<SessionDto>(resultDto, HttpStatus.OK);
		}

		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@GetMapping(value = "/{idSession}/utilisateurs")
	public ResponseEntity<List<String>> findAllUtilisateurBySession(@PathVariable int idSession) {

		List<String> participantsList = sessionService.findParticipationsList(idSession);
		if (participantsList.size() != 0) {
			return new ResponseEntity<List<String>>(participantsList, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);

	}

}
