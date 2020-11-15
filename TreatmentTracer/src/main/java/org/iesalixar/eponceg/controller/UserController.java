package org.iesalixar.eponceg.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.iesalixar.eponceg.model.Disease;
import org.iesalixar.eponceg.model.Role;
import org.iesalixar.eponceg.model.State;
import org.iesalixar.eponceg.model.User;
import org.iesalixar.eponceg.repository.UserRepository;
import org.iesalixar.eponceg.service.DiseaseService;
import org.iesalixar.eponceg.service.MedicalFileService;
import org.iesalixar.eponceg.service.RoleService;
import org.iesalixar.eponceg.service.StateService;
import org.iesalixar.eponceg.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

	@Autowired
	private UserRepository users;

	@Autowired
	private DiseaseService diseases;

	@Autowired
	private UserService userService;

	@Autowired
	private RoleService role;

	@Autowired
	private StateService state;
	
	boolean error = false;

	String emailCuidador = "";

	@RequestMapping("/user/home")
	public String home(@RequestParam(value = "disease", defaultValue = "-1") String id, Model model) {

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails userDetails = null;
		if (principal instanceof UserDetails) {
			userDetails = (UserDetails) principal;
		}
		String email = userDetails.getUsername();
		Optional<User> u = this.users.findByEmail(email);
		Set<User> users = new HashSet<>();
		users.add(u.get());
		Integer idI = Integer.parseInt(id);
		Long idL = Long.valueOf(idI);
		
		if (!(idL==-1)) {
			Disease d = diseases.readSelectedDisease(idL);
			model.addAttribute("selectedDisease", d);
		} else {
			model.addAttribute("selectedDisease", this.diseases.findFirstByUsersIn(users));
		}
		 //Copiar en los demás controladores para que se desactiven los enlaces
		if(this.diseases.readDiseases(users).isEmpty()) {
			model.addAttribute("withoutDisease", true);
		}
		model.addAttribute("diseases", this.diseases.readDiseases(users));
		model.addAttribute("allDisease", this.diseases.selectAllNotRepeat(users));
		model.addAttribute("role", 1);

		if (u.get().getCareer() != null) {
			model.addAttribute("volver", true);
			User career = u.get().getCareer();
			model.addAttribute("career", career.getEmail());

		}
		return "home";

	}

	@GetMapping("/career/home")
	public String careerHome(Model model) {

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails userDetails = null;
		if (principal instanceof UserDetails) {
			userDetails = (UserDetails) principal;
		}

		Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
		for (GrantedAuthority grantedAuthority : authorities) {
			if (grantedAuthority.getAuthority().equals("career")) {
				this.emailCuidador = userDetails.getUsername();
			}
		}

		Optional<User> u = this.users.findByEmail(emailCuidador);
		model.addAttribute("patients", this.userService.ListPatientOfACareer(u.get()));
		model.addAttribute("role", 2);
		model.addAttribute("error", error);
		error=false;
		return "home";

	}

	@SuppressWarnings("deprecation")
	@RequestMapping(value = { "/register" }, method = { RequestMethod.POST, RequestMethod.PUT, RequestMethod.GET })
	public String register(@RequestParam(value = "name") String name, @RequestParam(value = "email2") String email,
			@RequestParam(value = "surname") String surname, @RequestParam(value = "role") String role,
			@RequestParam(value = "pass") String password, @RequestParam(value = "dateOfBirth") String date,
			Model model) throws ParseException {

		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(4);

		String pass = bCryptPasswordEncoder.encode(password);

		Role r = this.role.findFirstById(Long.parseLong(role));
		Set<Role> roles = new HashSet<>();
		roles.add(r);

		State s = this.state.findFirstById(1L);

		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

		Date dateOfBirth = formato.parse(date);
		dateOfBirth.setDate(dateOfBirth.getDate() + 1);

		User u = new User(name, surname, email, pass, dateOfBirth);
		u.setState(s);
		u.setRole(roles);
		u.setDischargeDate(new Date());
		try {
			this.userService.createUser(u);

			Set<User> users = new HashSet<>();
			users.add(u);
			r.setUsers(users);
			error=false;
		}catch(Exception e){
			error=true;
		}
		
		model.addAttribute("error", error);
		return "index";
	}

	@SuppressWarnings("deprecation")
	@RequestMapping(value = { "/career/register" }, method = { RequestMethod.POST, RequestMethod.PUT,
			RequestMethod.GET })
	public String registerPatientForACareer(@RequestParam(value = "name") String name,
			@RequestParam(value = "email2") String email, @RequestParam(value = "surname") String surname,
			@RequestParam(value = "dateOfBirth") String date, Model model) throws ParseException {

		Optional<User> user = this.users.findByEmail(this.emailCuidador);

		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(4);

		String password = bCryptPasswordEncoder.encode(name);

		Role r = this.role.findFirstById(1L);
		Set<Role> roles = new HashSet<>();
		roles.add(r);

		State s = this.state.findFirstById(1L);

		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

		Date dateOfBirth = formato.parse(date);
		dateOfBirth.setDate(dateOfBirth.getDate() + 1);

		User u = new User(name, surname, email, password, dateOfBirth);
		u.setState(s);
		u.setRole(roles);
		u.setDischargeDate(new Date());
		u.setCareer(user.get());
		try {
			this.userService.createUser(u);

			Set<User> users = new HashSet<>();
			users.add(u);
			r.setUsers(users);
			error=false;
		}catch(Exception e) {
			error=true;
		}
		

		return "redirect:/career/home";
	}

	@RequestMapping(value = { "/removeUser" }, method = { RequestMethod.POST, RequestMethod.DELETE })
	public String deleteUser(@RequestParam(value = "id") String id, Model model) {
		this.userService.deletePatient(id);
		return "redirect:/career/home";
	}

	@RequestMapping(value = { "/updateUser" }, method = { RequestMethod.POST, RequestMethod.PUT, RequestMethod.GET })
	public String updateUser(@RequestParam(value = "patientId") String id, @RequestParam(value = "patientName") String name,
			@RequestParam(value = "patientSurname") String surname, @RequestParam(value = "patientEmail") String email,
			Model model) {
		User u = this.users.findFirstById(Long.parseLong(id));
		
		u.setName(name);
		u.setSurname(surname);
		u.setEmail(email);
		
		this.userService.updateUser(u);
		return "redirect:/career/home";
	}
	
	@GetMapping(value = { "/admin/patients" })
	public String patientsAdmin(Model model) {

		model.addAttribute("inactivePatient", this.userService.listPatientInactive());
		model.addAttribute("activePatient", this.userService.listPatientActive());
		model.addAttribute("allPatients", this.userService.listPatients());
		return "patients";
	}
	
	@GetMapping(value = { "/admin/carers" })
	public String carersAdmin(Model model) {

		model.addAttribute("inactiveCarer", this.userService.listCarerInactive());
		model.addAttribute("activeCarer", this.userService.listCarerActive());
		model.addAttribute("allCarers", this.userService.listCarers());
		return "carers";
	}
	
	@RequestMapping(value = { "/admin/activePatient" }, method = { RequestMethod.POST, RequestMethod.PUT })
	public String activePatient(@RequestParam(value = "id") String id, Model model) {
		User u = this.userService.search(Long.parseLong(id));
		this.userService.active(u);
		return "redirect:/admin/patients";
	}
	
	@RequestMapping(value = { "/admin/inactivePatient" }, method = { RequestMethod.POST, RequestMethod.PUT })
	public String inactivePatient(@RequestParam(value = "id") String id, Model model) {
		User u = this.userService.search(Long.parseLong(id));
		this.userService.deactive(u);
		return "redirect:/admin/patients";
	}
	
	@RequestMapping(value = { "/admin/deletePatient" }, method = { RequestMethod.POST, RequestMethod.DELETE })
	public String deletePatient(@RequestParam(value = "id") String id, Model model) {
		this.userService.deletePatient(id);
		return "redirect:/admin/patients";
	}
	
	@RequestMapping(value = { "/admin/activeCarer" }, method = { RequestMethod.POST, RequestMethod.PUT })
	public String activeCarer(@RequestParam(value = "id") String id, Model model) {
		User u = this.userService.search(Long.parseLong(id));
		this.userService.active(u);
		return "redirect:/admin/carers";
	}
	
	@RequestMapping(value = { "/admin/inactiveCarer" }, method = { RequestMethod.POST, RequestMethod.PUT })
	public String inactiveCarer(@RequestParam(value = "id") String id, Model model) {
		User u = this.userService.search(Long.parseLong(id));
		this.userService.deactive(u);
		return "redirect:/admin/carers";
	}
	
	@RequestMapping(value = { "/admin/deleteCarer" }, method = { RequestMethod.POST, RequestMethod.DELETE })
	public String deleteCarer(@RequestParam(value = "id") String id, Model model) {
		this.userService.deleteCarer(Long.parseLong(id));
		return "redirect:/admin/carers";
	}

}
