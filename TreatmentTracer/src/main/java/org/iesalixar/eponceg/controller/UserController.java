package org.iesalixar.eponceg.controller;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.iesalixar.eponceg.model.User;
import org.iesalixar.eponceg.repository.UserRepository;
import org.iesalixar.eponceg.service.DiseaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

	@Autowired
	private UserRepository users;
	
	@Autowired
	private DiseaseService diseases;
	
	@GetMapping("/user/home")
	public String home(Model model) {
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails userDetails = null;
		if (principal instanceof UserDetails) {
		  userDetails = (UserDetails) principal;
		}
		String email = userDetails.getUsername();
		Optional<User> u = this.users.findByEmail(email);
		Set<User> users = new HashSet<>();
		users.add(u.get());
		model.addAttribute("diseases", this.diseases.readDiseases(users));
		model.addAttribute("allDisease", this.diseases.selectAllNotRepeat(users));
		return "home";
		
	}	
	
}
