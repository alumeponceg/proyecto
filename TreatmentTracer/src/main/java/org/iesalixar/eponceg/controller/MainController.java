package org.iesalixar.eponceg.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	

	@GetMapping({"/", "/login"})
	public String welcome(Model model) {
		
		return "index";
	}
	
	
}
