package org.iesalixar.eponceg.controller;

import java.util.HashSet;
import java.util.Set;

import org.iesalixar.eponceg.model.Disease;
import org.iesalixar.eponceg.model.User;
import org.iesalixar.eponceg.service.DiseaseService;
import org.iesalixar.eponceg.service.StateService;
import org.iesalixar.eponceg.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DiseaseController {
	
	final static Logger logger = LoggerFactory.getLogger(DiseaseController.class);


	@Autowired
	private DiseaseService diseases;

	
	@Autowired
	private UserService user;
	
	@Autowired
	private StateService state;
	
	private boolean error=false;

	@RequestMapping(value = { "/user/update" }, method = { RequestMethod.POST, RequestMethod.PUT })
	public String removeDisease(@RequestParam(value = "diseaseId") String id, Model model) {

		Integer idI = Integer.parseInt(id);
		Long idL = Long.valueOf(idI);
		Disease d = diseases.readSelectedDisease(idL);
		User user = this.user.userLogged();
		this.diseases.unlinkDisease(user, d);
		logger.warn("Se ha desvinculado la enfermedad, borrado todos los tratamientos y las rutinas asociadas a la enfermedad");
		return "redirect:/user/home";
	}

	@RequestMapping(value = { "/user/addDisease" }, method = { RequestMethod.POST, RequestMethod.PUT })
	public String addNewDisease(@RequestParam(value = "newDisease") String id, Model model) {

		Integer idI = Integer.parseInt(id);
		Long idL = Long.valueOf(idI);
		Disease d = diseases.readSelectedDisease(idL);
		User u = this.user.userLogged();
		Set<Disease> diseases = u.getDiseases();
		diseases.add(d);
		u.setDiseases(diseases);
		user.save(u);

		Set<User> usuarios = d.getUsers();
		usuarios.add(u);
		d.setUsers(usuarios);
		this.diseases.updateDisease(d);
		logger.warn("La enfermedad " + d.getName() + " se ha asociado al usuario " + u.getId());
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
		
		User user = this.user.userLogged();
		Set<User> usuarios = new HashSet<>();
		usuarios.add(user);
		d.setUsers(usuarios);
		this.diseases.createDisease(d);
		
		Set<Disease> diseases = user.getDiseases();
		diseases.add(d);
		user.setDiseases(diseases);
		this.user.save(user);
		
		
		
		this.diseases.updateDisease(d);
		
		logger.warn("El usuario " + user.getId() + " ha registrado una nueva enfermedad inactiva. El administrador debe revisarla.");
		
		return "redirect:/user/home";
	}
	

	@GetMapping(value = { "/admin/home" })
	public String homeAdmin(Model model) {
		model.addAttribute("role", 3);
		model.addAttribute("inactiveDisease", this.diseases.listInactive());
		
		logger.warn("Se ha cargado la lista de enfermedades inactivas en el perfil administrador");
		return "home";
	}
	
	@RequestMapping(value = { "/admin/editDisease" }, method = { RequestMethod.POST, RequestMethod.PUT })
	public String editDisease(@RequestParam(value = "inactiveId") String id, Model model) {

		model.addAttribute("inactiveDisease", this.diseases.readSelectedDisease(Long.parseLong(id)));
		logger.warn("Se han mostrado los datos de la enfermedad inactiva seleccionada");
		return "editDisease";
	}
	
	@RequestMapping(value = { "/admin/delete" }, method = { RequestMethod.POST, RequestMethod.PUT })
	public String delete(@RequestParam(value = "inactiveId") String id, Model model) {

		this.diseases.deleteById(Long.parseLong(id));
		logger.warn("El administrador ha borrado la enfermedad " + id);
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
			logger.warn("Se ha editado y actualizado la enfermedad " + d.getName());
			error=false;
		}catch(Exception e) {
			logger.warn("No se ha podido actualizar la enfermedad " + d.getName());
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
			logger.warn("Se ha añadido una enfermedad nueva: " + d.getName());
			error=false;
		}catch(Exception e) {
			this.error=true;
			logger.warn("No se ha podido añadir la enfermedad nueva: " + d.getName());
		}
		
		return "redirect:/admin/diseases";
	}
	
	@GetMapping(value = { "/admin/diseases" })
	public String diseasesAdmin(Model model) {

		model.addAttribute("inactiveDisease", this.diseases.listInactive());
		model.addAttribute("allDiseases", this.diseases.listAllDisease());
		model.addAttribute("error", error);
		error=false;
		logger.warn("Se ha cargado la página 'Enfermedades' del perfil de administrador.");
		return "diseases";
	}
	
	
	

}
