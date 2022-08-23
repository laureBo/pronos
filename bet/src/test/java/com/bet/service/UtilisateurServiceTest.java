package com.bet.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import com.bet.BetApplicationTests;
import com.bet.model.dto.UtilisateurDto;
import com.bet.model.entity.UtilisateurEntity;
import com.bet.model.repository.IUtilisateurRepository;

@ExtendWith(MockitoExtension.class)
public class UtilisateurServiceTest extends BetApplicationTests {
	@Mock
	private IUtilisateurRepository utilisateurRepo;

	@Autowired
	@InjectMocks
	private UtilisateurService utilisateurService;

	@Test
	void testGetByPseudo() {

		when(utilisateurRepo.findByPseudoUser("Roro")).thenCallRealMethod();

		UtilisateurDto utilisateur = utilisateurService.getByPseudo("Roro");
		String expected = "Rom";
		String actual = utilisateur.getPrenom();

		assertEquals(expected, actual);
	};

	@Test
	void testGetByPseudoMock() {
		UtilisateurEntity utilisateurEntity = new UtilisateurEntity();
		utilisateurEntity.setPseudoUser("Rico");
		utilisateurEntity.setNomUser("Hitro");
		utilisateurEntity.setPrenomUser("zirka");
		utilisateurEntity.setMailUser("ricotro@orange.fr");

		// GIVEN
		when(utilisateurRepo.findByPseudoUser(any(String.class))).thenReturn(utilisateurEntity);

		// WHEN
		// verify(toto, times(1));
		UtilisateurDto resultTest = utilisateurService.getByPseudo("jaja");

		// THEN
		assertEquals("Rico", resultTest.getPseudo());
		assertEquals("zirka", resultTest.getPrenom());
	}

	@Test
	void testCountUtilisateur() {
		List<UtilisateurDto> utilisateurs = utilisateurService.findAllUtilisateur();
		int expected = 12;
		int actual = utilisateurs.size();

		assertEquals(expected, actual);
	}
}
