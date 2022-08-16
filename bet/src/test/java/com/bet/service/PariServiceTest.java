package com.bet.service;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.bet.BetApplicationTests;
import com.bet.model.entity.PariEntity;

public class PariServiceTest extends BetApplicationTests {

	@Autowired
	private PariService pariService;

	@Test
	void testGetAllByPseudo() {
		List<PariEntity> result = pariService.getAllByPseudo("Roro");
		System.out.println(result.size());

	};

	/*
	 * @Test void testGetThreeLastByPseudo() { List<PariEntity> result =
	 * pariService.getThreeLastByPseudo("Mat"); for (PariEntity p : result) {
	 * System.out.println(p.getEquipe1()); } }
	 */

}
