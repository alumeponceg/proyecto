package org.iesalixar.eponceg.controller;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.iesalixar.eponceg.model.Disease;
import org.iesalixar.eponceg.model.User;
import org.iesalixar.eponceg.repository.UserRepository;
import org.iesalixar.eponceg.service.DiseaseService;
import org.iesalixar.eponceg.service.RoutineService;
import org.iesalixar.eponceg.service.StateService;
import org.iesalixar.eponceg.service.TreatmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DiseaseController {

	@Autowired
	private DiseaseService diseases;

	@Autowired
	private UserRepository users;
	
	@Autowired
	private TreatmentService treatments;
	
	@Autowired
	private RoutineService routines;
	
	@Autowired
	private StateService state;
	
	private boolean error=false;

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
		this.diseases.unlinkDisease(user, d);
		return "redirect:/user/home";
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
		return "redirect:/user/home";
	}
	
	
	@RequestMapping(value = { "/user/addInactive" }, method = { RequestMethod.POST, RequestMethod.PUT })
	public String registerInactive(@RequestParam(value="name") String name,  Model model) {

		
		Disease d = new Disease();
		d.setState(this.state.findFirstById(2L));
		d.setDescription(null);
		d.setName(name);
		d.setCauses(null);
		d.setImage(null);
		d.setSymptom(null);
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails userDetails = null;
		if (principal instanceof UserDetails) {
			userDetails = (UserDetails) principal;
		}
		String email = userDetails.getUsername();
		Optional<User> u = this.users.findByEmail(email);
		User user = u.get();
		Set<User> usuarios = new HashSet<>();
		usuarios.add(user);
		d.setUsers(usuarios);
		this.diseases.createDisease(d);
		
		Set<Disease> diseases = user.getDiseases();
		diseases.add(d);
		user.setDiseases(diseases);
		users.save(user);
		
		
		
		this.diseases.updateDisease(d);
		
		return "redirect:/user/home";
	}
	

	@GetMapping(value = { "/admin/home" })
	public String homeAdmin(Model model) {
		model.addAttribute("role", 3);
		model.addAttribute("inactiveDisease", this.diseases.listInactive());
		return "home";
	}
	
	@RequestMapping(value = { "/admin/editDisease" }, method = { RequestMethod.POST, RequestMethod.PUT })
	public String editDisease(@RequestParam(value = "inactiveId") String id, Model model) {

		model.addAttribute("inactiveDisease", this.diseases.readSelectedDisease(Long.parseLong(id)));
		return "editDisease";
	}
	
	@RequestMapping(value = { "/admin/delete" }, method = { RequestMethod.POST, RequestMethod.PUT })
	public String delete(@RequestParam(value = "inactiveId") String id, Model model) {

		this.diseases.deleteById(Long.parseLong(id));
		return "redirect:/admin/home";
	}
	
	@RequestMapping(value = { "/admin/edit" }, method = { RequestMethod.POST, RequestMethod.PUT })
	public String editar(@RequestParam(value = "editId") String id, @RequestParam(value = "nameDisease") String name, @RequestParam(value = "symptomDisease") String symptom,  @RequestParam(value = "descriptionDisease") String description, @RequestParam(value = "causesDisease") String causes, @RequestParam(value="pic") String pic,  Model model) {

		Disease d = this.diseases.readSelectedDisease(Long.parseLong(id));
		
		d.setState(this.state.findFirstById(1L));
		d.setDescription(description);
		d.setName(name);
		d.setCauses(causes);
		d.setImage(pic);
		d.setSymptom(symptom);
		
		try {
			this.diseases.updateDisease(d);
			error=false;
		}catch(Exception e) {
			System.out.println("hay un problemiya");
			this.error=true;
		}
		
		
		return "redirect:/admin/diseases";
	}
	
	@RequestMapping(value = { "/admin/addDisease" }, method = { RequestMethod.POST, RequestMethod.PUT })
	public String registerDisease( @RequestParam(value = "nameDisease") String name, @RequestParam(value = "symptomDisease") String symptom,  @RequestParam(value = "descriptionDisease") String description, @RequestParam(value = "causesDisease") String causes, @RequestParam(value="pic") String pic,  Model model) {

		
		Disease d = new Disease();
		d.setState(this.state.findFirstById(1L));
		d.setDescription(description);
		d.setName(name);
		d.setCauses(causes);
		d.setImage(pic);
		d.setSymptom(symptom);
		try {
			this.diseases.createDisease(d);
			error=false;
		}catch(Exception e) {
			this.error=true;
		}
		
		return "redirect:/admin/diseases";
	}
	
	@GetMapping(value = { "/admin/diseases" })
	public String diseasesAdmin(Model model) {

		model.addAttribute("inactiveDisease", this.diseases.listInactive());
		model.addAttribute("allDiseases", this.diseases.listAllDisease());
		model.addAttribute("error", error);
		error=false;
		return "diseases";
	}
	
	
	

}
