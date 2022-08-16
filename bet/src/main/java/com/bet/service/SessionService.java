package com.bet.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bet.model.repository.IPariRepository;
import com.bet.model.repository.ISessionRepository;

@Service
public class SessionService {

	private static Logger logger = LoggerFactory.getLogger(SessionService.class);
	@Autowired
	private ISessionRepository sessionRepository;

	@Autowired
	private IPariRepository pariRepository;

	public List<String> getAllNomSessionByUserList(String pseudo) {

		return sessionRepository.findAllNomSessionByUser(pseudo);

	}
}
