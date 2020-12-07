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
import org.iesalixar.eponceg.service.RoleService;
import org.iesalixar.eponceg.service.StateService;
import org.iesalixar.eponceg.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	
	final static Logger logger = LoggerFactory.getLogger(UserController.class);

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
	
	@Autowired
	private UserService user;
	
	boolean error = false;

	String emailCuidador = "";

	@RequestMapping("/user/home")
	public String home(@RequestParam(value = "disease", defaultValue = "-1") String id, Model model) {
		
		

		User user = this.user.userLogged();
		Set<User> users = new HashSet<>();
		users.add(user);
		Integer idI = Integer.parseInt(id);
		Long idL = Long.valueOf(idI);
		
		if (!(idL==-1)) {
			Disease d = diseases.readSelectedDisease(idL);
			model.addAttribute("selectedDisease", d);
			logger.warn("Se ha mostrado la información de la enfermedad seleccionada (" + d.getName() + ")");
		} else {
			model.addAttribute("selectedDisease", this.diseases.findFirstByUsersIn(users));
		}
		
		if(this.diseases.readDiseases(users).isEmpty()) {
			model.addAttribute("withoutDisease", true);
			logger.warn("El usuario no tiene ninguna enfermedad asociada, se va a mostrar un mensaje informativo");
		}
		model.addAttribute("diseases", this.diseases.readDiseases(users));
		
		model.addAttribute("allDisease", this.diseases.selectAllNotRepeat(users) );
		model.addAttribute("role", 1);

		if (user.getCareer() != null) {
			model.addAttribute("volver", true);
			User career = user.getCareer();
			model.addAttribute("career", career.getEmail());

		}
		logger.warn("El usuario " + user.getId() + " ha entrado en la página principal de usuarios con rol paciente" );
		return "home";
		

	}

	@RequestMapping("/career/home")
	public String careerHome(@RequestParam(value = "volver", defaultValue = "0") String volver ,@RequestParam(value = "name", defaultValue="") String name , @RequestParam(value = "order", defaultValue = "null") String order, Model model) {

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails userDetails = null;
		if (principal instanceof UserDetails) {
			userDetails = (UserDetails) principal;
		}
		Optional<User> u= null;
		Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
		for (GrantedAuthority grantedAuthority : authorities) {
			if (grantedAuthority.getAuthority().equals("career")) {
				this.emailCuidador = userDetails.getUsername();
				
			}else if(volver.equals("1")) {
				 u =this.users.findByEmail(emailCuidador);
			}else if(!grantedAuthority.getAuthority().equals("career") && volver.equals("0")){
				logger.warn("El usuario " + u.get().getId() + " no tiene permisos para acceder a esta ruta" );
				return "index";
			}
		}
		
		
		u =this.users.findByEmail(emailCuidador);
		switch (order) {
		case "1":
			model.addAttribute("patients", this.userService.ListPatientOfACareerByEmail(u.get()));
			logger.warn("Los pacientes del usuario " + u.get().getId() + " han sido ordenados segun su email" );
			break;
		case "2":
			model.addAttribute("patients", this.userService.ListPatientOfACareerByName(u.get()));
			logger.warn("Los pacientes del usuario " + u.get().getId() + " han sido ordenados segun su nombre" );
			break;
		case "3":
			model.addAttribute("patients", this.userService.ListPatientOfACareerByNameDesc(u.get()));
			logger.warn("Los pacientes del usuario " + u.get().getId() + " han sido ordenados segun su nombre en orden descendente" );
			break;
		case "4":
			if (!name.equals("")) {
				model.addAttribute("patients", this.userService.ListPatientOfACareerAndName(u.get(), name));
				logger.warn("Los pacientes del usuario " + u.get().getId() + " con el nombre " + name + " han sido mostrados." );
			}else {
				model.addAttribute("patients", this.userService.ListPatientOfACareer(u.get()));
			}
			break;
		default:
			model.addAttribute("patients", this.userService.ListPatientOfACareer(u.get()));
			logger.warn("Los pacientes del usuario " + u.get().getId() + " han sido mostrados" );
			break;
		}
		
		model.addAttribute("role", 2);
		model.addAttribute("error", error);
		error=false;
		
		logger.warn("El usuario " + u.get().getId() + " ha entrado en la página principal de usuarios con rol cuidador" );
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
			logger.warn("El usuario ha sido dado de alta con éxito en la aplicación con el rol " + r.getName() );
			error=false;
		}catch(Exception e){
			error=true;
			logger.warn("El usuario no ha podido ser dado de alta en la aplicación" );
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
			logger.warn("El cuidador " + user.get().getId() + " ha registrado un nuevo paciente a su cargo." );
		}catch(Exception e) {
			error=true;
			logger.warn("No se ha podido registrar el paciente a cargo" );
		}
		

		return "redirect:/career/home";
	}

	@RequestMapping(value = { "/career/removeUser" }, method = { RequestMethod.POST, RequestMethod.DELETE })
	public String deleteUser(@RequestParam(value = "id") String id, Model model) {
		this.userService.deletePatient(id);
		logger.warn("El usuario cuidador ha eliminado a su paciente a cargo con id " + id + " y por tanto todas las referencias al mismo en el aplicativo" );
		return "redirect:/career/home";
	}

	@RequestMapping(value = { "/career/updateUser" }, method = { RequestMethod.POST, RequestMethod.PUT, RequestMethod.GET })
	public String updateUser(@RequestParam(value = "patientId") String id, @RequestParam(value = "patientName") String name,
			@RequestParam(value = "patientSurname") String surname, @RequestParam(value = "patientEmail") String email,
			Model model) {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(4);

		String password = bCryptPasswordEncoder.encode(name);
		User u = this.users.findFirstById(Long.parseLong(id));
		u.setPassword(password);
		u.setName(name);
		u.setSurname(surname);
		u.setEmail(email);
		
		this.userService.updateUser(u);
		logger.warn("El usuario ha editado los datos de uno de sus pacientes a cargo" );
		return "redirect:/career/home";
	}
	
	@GetMapping(value = { "/admin/patients" })
	public String patientsAdmin(Model model) {

		model.addAttribute("inactivePatient", this.userService.listPatientInactive());
		model.addAttribute("activePatient", this.userService.listPatientActive());
		model.addAttribute("allPatients", this.userService.listPatients());
		logger.warn("Se ha cargado la vista 'Pacientes' para el rol administrador" );
		return "patients";
	}
	
	@GetMapping(value = { "/admin/carers" })
	public String carersAdmin(Model model) {

		model.addAttribute("inactiveCarer", this.userService.listCarerInactive());
		model.addAttribute("activeCarer", this.userService.listCarerActive());
		model.addAttribute("allCarers", this.userService.listCarers());
		logger.warn("Se ha cargado la vista 'Cuidadores' para el rol administrador" );
		return "carers";
	}
	
	@RequestMapping(value = { "/admin/activePatient" }, method = { RequestMethod.POST, RequestMethod.PUT })
	public String activePatient(@RequestParam(value = "id") String id, Model model) {
		User u = this.userService.search(Long.parseLong(id));
		this.userService.active(u);
		logger.warn("Un administrador ha activado el paciente " + u.getId() );
		return "redirect:/admin/patients";
	}
	
	@RequestMapping(value = { "/admin/inactivePatient" }, method = { RequestMethod.POST, RequestMethod.PUT })
	public String inactivePatient(@RequestParam(value = "id") String id, Model model) {
		User u = this.userService.search(Long.parseLong(id));
		this.userService.deactive(u);
		logger.warn("Un administrador ha desactivado el paciente " + u.getId() );
		return "redirect:/admin/patients";
	}
	
	@RequestMapping(value = { "/admin/deletePatient" }, method = { RequestMethod.POST, RequestMethod.DELETE })
	public String deletePatient(@RequestParam(value = "id") String id, Model model) {
		this.userService.deletePatient(id);
		logger.warn("Un administrador ha borrado el usuario " + id + " con rol paciente, y todas sus referencias han sido borradas de la aplicación");
		return "redirect:/admin/patients";
	}
	
	@RequestMapping(value = { "/admin/activeCarer" }, method = { RequestMethod.POST, RequestMethod.PUT })
	public String activeCarer(@RequestParam(value = "id") String id, Model model) {
		User u = this.userService.search(Long.parseLong(id));
		this.userService.active(u);
		logger.warn("Un administrador ha activado el cuidador " + u.getId() );
		return "redirect:/admin/carers";
	}
	
	@RequestMapping(value = { "/admin/inactiveCarer" }, method = { RequestMethod.POST, RequestMethod.PUT })
	public String inactiveCarer(@RequestParam(value = "id") String id, Model model) {
		User u = this.userService.search(Long.parseLong(id));
		this.userService.deactive(u);
		logger.warn("Un administrador ha desactivado el cuidador " + u.getId() );
		return "redirect:/admin/carers";
	}
	
	@RequestMapping(value = { "/admin/deleteCarer" }, method = { RequestMethod.POST, RequestMethod.DELETE })
	public String deleteCarer(@RequestParam(value = "id") String id, Model model) {
		this.userService.deleteCarer(Long.parseLong(id));
		logger.warn("Un administrador ha elimidado el cuidador " + id + " por tanto tanto sus pacientes a cargo como sus referencias se han eliminado de la aplicación" );
		return "redirect:/admin/carers";
	}

}
