package com.bet.controler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bet.model.dto.PariInputDto;
import com.bet.service.PariService;

@RestController
@RequestMapping(value = "/paris")
public class PariController {
	private static Logger logger = LoggerFactory.getLogger(PariController.class);

	@Autowired
	private PariService pariService;

	@PostMapping(value = "/save")
	public ResponseEntity<String> saveParis(@RequestBody PariInputDto input) {
		logger.info("enregistrer un pari: saveParis");
		pariService.savePariDto(input);
		return new ResponseEntity<String>("/paris/", HttpStatus.ACCEPTED);
	}

}
