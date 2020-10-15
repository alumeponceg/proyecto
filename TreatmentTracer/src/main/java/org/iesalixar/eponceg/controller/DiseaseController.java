package org.iesalixar.eponceg.controller;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.iesalixar.eponceg.model.Disease;
import org.iesalixar.eponceg.model.User;
import org.iesalixar.eponceg.repository.UserRepository;
import org.iesalixar.eponceg.service.DiseaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DiseaseController {

	@Autowired
	private DiseaseService diseases;

	@Autowired
	private UserRepository users;

	@RequestMapping(value = { "/user/disease" }, method = { RequestMethod.POST })
	public String createStudent(@RequestParam(value = "disease") String id, Model model) {

		Integer idI = Integer.parseInt(id);
		Long idL = Long.valueOf(idI);
		if (!idL.equals(-1)) {
			Disease d = diseases.readSelectedDisease(idL);
			model.addAttribute("selectedDisease", d);
		} else {
			model.addAttribute("selectedDisease", null);
		}

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

	@RequestMapping(value = { "/user/update" }, method = { RequestMethod.POST, RequestMethod.PUT })
	public String removeDisease(@RequestParam(value = "diseaseId") String id, Model model) {

		Integer idI = Integer.parseInt(id);
		Long idL = Long.valueOf(idI);
		Disease d = diseases.readSelectedDisease(idL);
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails userDetails = null;
		if (principal instanceof UserDetails) {
			userDetails = (UserDetails) principal;
		}
		String email = userDetails.getUsername();
		Optional<User> u = this.users.findByEmail(email);
		User user = u.get();
		Set<User> users = new HashSet<>();
		users.add(user);
		Set<Disease> diseases = user.getDiseases();
		diseases.remove(d);
		user.setDiseases(diseases);
		this.users.save(user);

		Set<User> usuarios = d.getUsers();
		usuarios.remove(user);
		d.setUsers(usuarios);
		this.diseases.updateDisease(d);
		model.addAttribute("diseases", user.getDiseases());
		model.addAttribute("allDisease", this.diseases.selectAllNotRepeat(users));
		return "home";
	}

	@RequestMapping(value = { "/user/addDisease" }, method = { RequestMethod.POST, RequestMethod.PUT })
	public String addNewDisease(@RequestParam(value = "newDisease") String id, Model model) {

		Integer idI = Integer.parseInt(id);
		Long idL = Long.valueOf(idI);
		Disease d = diseases.readSelectedDisease(idL);
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails userDetails = null;
		if (principal instanceof UserDetails) {
			userDetails = (UserDetails) principal;
		}
		String email = userDetails.getUsername();
		Optional<User> u = this.users.findByEmail(email);
		User user = u.get();
		Set<Disease> diseases = user.getDiseases();
		diseases.add(d);
		user.setDiseases(diseases);
		users.save(user);

		Set<User> usuarios = d.getUsers();
		usuarios.add(user);
		d.setUsers(usuarios);
		this.diseases.updateDisease(d);
		model.addAttribute("diseases", user.getDiseases());
		model.addAttribute("allDisease", this.diseases.selectAllNotRepeat(usuarios));
		return "home";
	}

}
