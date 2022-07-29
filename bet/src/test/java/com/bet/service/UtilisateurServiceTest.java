package com.bet.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.bet.BetApplicationTests;
import com.bet.model.dto.UtilisateurDto;

public class UtilisateurServiceTest extends BetApplicationTests {

	@Autowired
	private UtilisateurService utilisateurService;

	@Test
	void testGetByPseudo() {
		UtilisateurDto utilisateur = utilisateurService.getByPseudo("gege");
		String expected = "Gerard";
		String actual = utilisateur.getPrenom();

		assertEquals(expected, actual);
	};

	@Test
	void testCountUtilisateur() {
		List<UtilisateurDto> utilisateurs = utilisateurService.findAllUtilisateur();
		int expected = 1;
		int actual = utilisateurs.size();

		assertEquals(expected, actual);
	}
}
