package com.bet.controler;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bet.model.dto.PariDetailDto;
import com.bet.service.PariService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "/pari")
@Slf4j

public class PariController {
	private static Logger logger = LoggerFactory.getLogger(PariController.class);

	@Autowired
	private PariService pariService;

	@GetMapping(value = "/byUser/{pseudo}")
	public List<PariDetailDto> getParisDetailByUser(@PathVariable String pseudo) {

		logger.info("Info log message");
		return pariService.getThreeLastBetByPseudo2(pseudo);
	}
}
