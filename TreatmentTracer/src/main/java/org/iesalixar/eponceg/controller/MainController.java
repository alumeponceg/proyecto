package org.iesalixar.eponceg.controller;

import org.iesalixar.eponceg.model.Role;
import org.iesalixar.eponceg.model.User;
import org.iesalixar.eponceg.service.RoleService;
import org.iesalixar.eponceg.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	
	final static Logger logger = LoggerFactory.getLogger(MainController.class);
	@Autowired
	UserService users;
	
	@Autowired
	RoleService role;

	@GetMapping({"/", "/login"})
	public String welcome(Model model) {
		logger.warn("Se ha redireccionado al usuario a la página de inicio de sesión");
		return "index";
	}
	
	
	@GetMapping({"/error"})
	public String error(Model model) {
		return "error";
	}
	
	@GetMapping({"/return"})
	public String returned(Model model) {
		User u = this.users.userLogged();
		String redirect=null;
		Role user = this.role.findFirstById(1L);
		Role carer = this.role.findFirstById(2L);
		Role admin = this.role.findFirstById(3L);
		if (u.getRole().contains(user)) {
			redirect= "redirect:/user/home";
		}else if (u.getRole().contains(carer)) {
			redirect= "redirect:/career/home";
		}else if (u.getRole().contains(admin)) {
			redirect= "redirect:/admin/home";
		}
		
		return redirect;
	}
	
	
}
