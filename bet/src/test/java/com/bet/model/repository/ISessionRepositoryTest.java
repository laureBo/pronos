package com.bet.model.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.bet.BetApplicationTests;

public class ISessionRepositoryTest extends BetApplicationTests {

	@Autowired
	private ISessionRepository sessionRepo;

	@Test
	void testFindAllNomSessionByUser() {
		List<String> result = sessionRepo.findAllNomSessionByUser("Zaza");
		System.out.println(result.size());
		assertEquals("Ligue des champions 2022", result.get(0));
	}
}
