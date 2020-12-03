package org.iesalixar.eponceg.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.iesalixar.eponceg.model.MedicalAppointment;
import org.iesalixar.eponceg.model.User;
import org.iesalixar.eponceg.service.DiseaseService;
import org.iesalixar.eponceg.service.MedicalAppointmentService;
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
public class MedicalAppointmentController {
	
	final static Logger logger = LoggerFactory.getLogger(MedicalAppointment.class);

	@Autowired 
	private MedicalAppointmentService medicalAppointments;
	
	@Autowired 
	private DiseaseService diseases;
	
	@Autowired
	private UserService user;
	
	@SuppressWarnings("deprecation")
	@GetMapping("/user/medicalAppointments")
	public String readMedical(Model model) {
		
		User u = this.user.userLogged();
		
		Date fechaActual = new Date();
		
		Integer mes = fechaActual.getMonth() + 1;
		Date fechaMesProximo = new Date();
		
		fechaMesProximo.setMonth(mes);
		Set<User> usuarios = new HashSet<>();
		usuarios.add(u);
		if(this.diseases.readDiseases(usuarios).isEmpty()) {
			model.addAttribute("withoutDisease", true);
		}
		model.addAttribute("allApp", this.medicalAppointments.ListForAnUser(u));
		logger.warn("Las citas del usuario " + u.getId() + " han sido mostradas");
		model.addAttribute("appointments", this.medicalAppointments.ListForTheNextMonth(u, fechaActual,  fechaMesProximo));
		logger.warn("Las citas del usuario " + u.getId() + " para los próximos 30 han sido mostradas");
		return "appointments";
		
	}	
	
	@SuppressWarnings("deprecation")
	@RequestMapping(value = { "/user/createAppointment" }, method = { RequestMethod.POST, RequestMethod.PUT,  RequestMethod.GET})
	public String createMedicalAppointment(@RequestParam(value = "specialty") String specialty, @RequestParam(value = "title") String title ,@RequestParam(value = "annotations") String annotations, @RequestParam(value = "date") String date, Model model) throws ParseException {
		
		SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        
        Date    fechaDate = formato.parse(date);
        fechaDate.setDate(fechaDate.getDate()+1);
		
		MedicalAppointment medical = new MedicalAppointment(title, specialty,annotations,fechaDate);
		
		User u = this.user.userLogged();
		medical.setUserId(u);
		
		this.medicalAppointments.createMedicalAppointment(medical);
		
		Set<MedicalAppointment> medicals = u.getMedicalAppointments();
		
		medicals.add(medical);
		
		u.setMedicalAppointments(medicals);
		
		this.user.save(u);
		logger.warn("Se ha anotado una nueva cita para el usuario con id  " + u.getId());
		return "redirect:/user/medicalAppointments";
	}
	
	@RequestMapping(value = { "/user/deleteAppointment" }, method = { RequestMethod.POST, RequestMethod.DELETE })
	public String deleteAppointment(@RequestParam(value = "id") String id, Model model) {
		this.medicalAppointments.delete(Long.parseLong(id));
		logger.warn("Se ha eliminado una cita médica");
		return "redirect:/user/medicalAppointments";
	}
}
