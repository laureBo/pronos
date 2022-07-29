package com.bet.controler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.bet.model.dto.UtilisateurDto;
import com.bet.service.UtilisateurService;

@RestController
@RequestMapping(value = "/utilisateur")

public class UtilisateurController {

	@Autowired
	private UtilisateurService utilisateurService;

	@GetMapping(value = "/findAll")
	public List<UtilisateurDto> getAllUtilisateur() {
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
