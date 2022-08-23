package com.bet.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import com.bet.BetApplicationTests;
import com.bet.model.dto.PariDetailDto;
import com.bet.model.entity.MatchEntity;
import com.bet.model.entity.PariEntity;
import com.bet.model.repository.IPariRepository;

@ExtendWith(MockitoExtension.class)
public class PariServiceTest extends BetApplicationTests {

	@Mock
	IPariRepository pariRepository;

	@Autowired
	@InjectMocks
	private PariService pariService;

	@Test
	void testGetAllByPseudo() {
		List<PariEntity> result = pariService.getAllByPseudo("Roro");
		System.out.println(result.size());

	}

	@Test
	public void getAllByPseudoAndNameSession() {
		MatchEntity matchEntity = new MatchEntity();
		matchEntity.setEquipe1("barca");
		matchEntity.setEquipe2("real");
		matchEntity.setScoreEquipe1(0);
		matchEntity.setScoreEquipe2(12);
		PariEntity entityTest = new PariEntity();
		entityTest.setIdPari(15);
		entityTest.setEquipe1(0);
		entityTest.setEquipe2(2);
		entityTest.setMatch(matchEntity);
		List<PariEntity> parisEntityList = new ArrayList<>();
		parisEntityList.add(entityTest);

		// GIVEN
		when(pariRepository.findAllBetsbyPseudoAndNomSession(any(String.class), any(String.class)))
				.thenReturn(parisEntityList);

		// WHEN
		List<PariDetailDto> parisEntityResultList = pariService.getAllByPseudoAndNameSession("pseudo1", "nomSession1");

		// THEN
		assertEquals(1, parisEntityResultList.size());
		assertEquals(0, parisEntityResultList.get(0).getPariEquipe1());
		assertEquals(2, parisEntityResultList.get(0).getPariEquipe2());
	}
}
