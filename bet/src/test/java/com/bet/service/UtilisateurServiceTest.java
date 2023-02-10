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

        when(this.utilisateurRepo.findByPseudoUser("Roro")).thenCallRealMethod();

        final UtilisateurDto utilisateur = this.utilisateurService.getByPseudo("Roro");
        final String expected = "Rom";
        final String actual = utilisateur.getPrenom();

        assertEquals(expected, actual);
    }

    @Test
    void testGetByPseudoMock() {
        final UtilisateurEntity utilisateurEntity = new UtilisateurEntity();
        utilisateurEntity.setPseudoUser("Rico");
        utilisateurEntity.setNomUser("Hitro");
        utilisateurEntity.setPrenomUser("zirka");
        utilisateurEntity.setMailUser("ricotro@orange.fr");

        // GESTION DU MOCK
        when(this.utilisateurRepo.findByPseudoUser(any(String.class))).thenReturn(utilisateurEntity);

        // APPEL DU SERVICE
        final UtilisateurDto resultTest = this.utilisateurService.getByPseudo("jaja");

        // CONTROLE DU RESULTAT
        assertEquals("Rico", resultTest.getPseudo());
        assertEquals("zirka", resultTest.getPrenom());
    }

    @Test
    void testCountUtilisateur() {
        final List<UtilisateurDto> utilisateurs = this.utilisateurService.findAllUtilisateur();
        final int expected = 12;
        final int actual = utilisateurs.size();

        assertEquals(expected, actual);
    }
}
