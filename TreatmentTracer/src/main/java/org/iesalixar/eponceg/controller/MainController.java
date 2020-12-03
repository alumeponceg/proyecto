package org.iesalixar.eponceg.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	
	final static Logger logger = LoggerFactory.getLogger(MainController.class);

	@GetMapping({"/", "/login"})
	public String welcome(Model model) {
		logger.warn("Se ha redireccionado al usuario a la página de inicio de sesión");
		return "index";
	}
	
	
}
