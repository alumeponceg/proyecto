package org.iesalixar.eponceg.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.iesalixar.eponceg.model.MedicalAppointment;
import org.iesalixar.eponceg.model.User;
import org.iesalixar.eponceg.repository.UserRepository;
import org.iesalixar.eponceg.service.DiseaseService;
import org.iesalixar.eponceg.service.MedicalAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MedicalAppointmentController {

	@Autowired 
	private MedicalAppointmentService medicalAppointments;
	
	@Autowired 
	private DiseaseService diseases;
	
	@Autowired
	private UserRepository users;
	
	@SuppressWarnings("deprecation")
	@GetMapping("/user/medicalAppointments")
	public String readMedical(Model model) {
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails userDetails = null;
		if (principal instanceof UserDetails) {
		  userDetails = (UserDetails) principal;
		}
		String email = userDetails.getUsername();
		Optional<User> u = this.users.findByEmail(email);
		User usuario = u.get();
		
		Date fechaActual = new Date();
		
		Integer mes = fechaActual.getMonth() + 1;
		Date fechaMesProximo = new Date();
		
		fechaMesProximo.setMonth(mes);
		Set<User> usuarios = new HashSet<>();
		usuarios.add(usuario);
		if(this.diseases.readDiseases(usuarios).isEmpty()) {
			model.addAttribute("withoutDisease", true);
		}
		model.addAttribute("allApp", this.medicalAppointments.ListForAnUser(usuario));
		model.addAttribute("appointments", this.medicalAppointments.ListForTheNextMonth(usuario, fechaActual,  fechaMesProximo));
		return "appointments";
		
	}	
	
	@SuppressWarnings("deprecation")
	@RequestMapping(value = { "/user/createAppointment" }, method = { RequestMethod.POST, RequestMethod.PUT,  RequestMethod.GET})
	public String createMedicalAppointment(@RequestParam(value = "specialty") String specialty, @RequestParam(value = "title") String title ,@RequestParam(value = "annotations") String annotations, @RequestParam(value = "date") String date, Model model) throws ParseException {
		
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        
        Date    fechaDate = formato.parse(date);
        fechaDate.setDate(fechaDate.getDate()+1);
		
		MedicalAppointment medical = new MedicalAppointment(title, specialty,annotations,fechaDate);
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails userDetails = null;
		if (principal instanceof UserDetails) {
		  userDetails = (UserDetails) principal;
		}
		String email = userDetails.getUsername();
		Optional<User> u = this.users.findByEmail(email);
		medical.setUserId(u.get());
		
		this.medicalAppointments.createMedicalAppointment(medical);
		
		Set<MedicalAppointment> medicals = u.get().getMedicalAppointments();
		
		medicals.add(medical);
		
		u.get().setMedicalAppointments(medicals);
		
		this.users.save(u.get());
		
		return "redirect:/user/medicalAppointments";
	}
	
	@RequestMapping(value = { "/user/deleteAppointment" }, method = { RequestMethod.POST, RequestMethod.DELETE })
	public String deleteAppointment(@RequestParam(value = "id") String id, Model model) {
		this.medicalAppointments.delete(Long.parseLong(id));
		return "redirect:/user/medicalAppointments";
	}
}
