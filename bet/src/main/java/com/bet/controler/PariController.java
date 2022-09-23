package com.bet.controler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bet.service.PariService;

@RestController
@RequestMapping(value = "/paris")
public class PariController {
	private static Logger logger = LoggerFactory.getLogger(PariController.class);

	@Autowired
	private PariService pariService;

}
