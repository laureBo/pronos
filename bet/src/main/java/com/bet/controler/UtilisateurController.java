package com.bet.controler;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.bet.model.dto.UtilisateurDto;
import com.bet.service.UtilisateurService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "/utilisateur")

@Slf4j
public class UtilisateurController {
	
	private static Logger logger = LoggerFactory.getLogger(UtilisateurController.class);


	@Autowired
	private UtilisateurService utilisateurService;

	@GetMapping(value = "/findAll")
	public List<UtilisateurDto> getAllUtilisateur() {
		logger.info("Info log message");
		return utilisateurService.findAllUtilisateur();
	}

	@GetMapping(value = "/findByPseudo/{pseudo}")
	public UtilisateurDto getUtilisateurByPseudo(@PathVariable String pseudo) {
		UtilisateurDto result = utilisateurService.getByPseudo(pseudo);
		if (result == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "object not found");
		}
		return result;
	}

}
