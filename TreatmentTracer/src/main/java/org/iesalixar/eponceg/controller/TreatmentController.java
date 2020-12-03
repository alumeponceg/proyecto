package org.iesalixar.eponceg.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.iesalixar.eponceg.model.Disease;
import org.iesalixar.eponceg.model.State;
import org.iesalixar.eponceg.model.Treatment;
import org.iesalixar.eponceg.model.User;
import org.iesalixar.eponceg.service.DiseaseService;
import org.iesalixar.eponceg.service.StateService;
import org.iesalixar.eponceg.service.TreatmentService;
import org.iesalixar.eponceg.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TreatmentController {
	
	final static Logger logger = LoggerFactory.getLogger(TreatmentController.class);

	
	@Autowired
	private TreatmentService treatments;
	
	@Autowired
	private StateService state;
	
	@Autowired
	private UserService user;
	
	@Autowired
	private DiseaseService disease;
	
	@RequestMapping(value ={"/user/treatments"},  method = { RequestMethod.POST, RequestMethod.GET})
	public String treatmentsOrder(@RequestParam(value = "order", defaultValue = "null") String order, Model model) {
		User u = this.user.userLogged();
		Set<User> users = new HashSet<>();
		users.add(u);
		List<Treatment> treatments = new ArrayList<>();
		
		switch (order) {
		case "1":
			 treatments = this.treatments.listTreatmentsOrderByName(u);
			
			for(Treatment t : treatments) {
				t.setDuration(t.getDuration()/24);
			}
			model.addAttribute("treatments", treatments);
			model.addAttribute("diseases", this.disease.readDiseases(users));
			logger.warn("Los tratamientos se han mostrado ordenados por nombre");
			break;
		case "2":
			 treatments = this.treatments.listTreatmentsOrderByNameDesc(u);
				
				for(Treatment t : treatments) {
					t.setDuration(t.getDuration()/24);
				}
				model.addAttribute("treatments", treatments);
			model.addAttribute("diseases", this.disease.readDiseases(users));
			logger.warn("Los tratamientos se han mostrado ordenados por nombre en orden descendente");
			break;
		case "3":
			 treatments = this.treatments.listTreatmentsOrderByDate(u);
				
				for(Treatment t : treatments) {
					t.setDuration(t.getDuration()/24);
				}
				model.addAttribute("treatments", treatments);
			model.addAttribute("diseases", this.disease.readDiseases(users));
			logger.warn("Los tratamientos se han mostrado ordenados por fecha de activación");
			break;
		case "4":
			 treatments =  this.treatments.listTreatmentsForAnUserOrderByDisease(u);
				
				for(Treatment t : treatments) {
					t.setDuration(t.getDuration()/24);
				}
				model.addAttribute("treatments", treatments);
			model.addAttribute("diseases", this.disease.readDiseases(users));
			logger.warn("Los tratamientos se han mostrado ordenados por enfermedad asociada");
			break;
		default:
			 treatments = this.treatments.listTreatmentsForAnUser(u);
				
				for(Treatment t : treatments) {
					t.setDuration(t.getDuration()/24);
				}
				model.addAttribute("treatments", treatments);
			model.addAttribute("diseases", this.disease.readDiseases(users));
			logger.warn("Los tratamientos se han mostrado en el orden predeterminado");
			break;
		}
		
		logger.warn("La pestaña 'Tratamientos' ha sido cargada correctamente");
		return "treatments";
	}

	@RequestMapping(value = { "/user/removeTreatment" }, method = { RequestMethod.POST, RequestMethod.DELETE })
	public String deleteTreatment(@RequestParam(value = "id") String id, Model model) {
		this.treatments.deleteTreatment(Long.parseLong(id));
		logger.warn("Se ha eliminado el tratamiento solicitado");
		return "redirect:/user/treatments";
	}
	
	@RequestMapping(value = { "/user/updateTreatment" }, method = { RequestMethod.POST, RequestMethod.PUT,  RequestMethod.GET})
	public String updateTreatment(@RequestParam(value = "id") String id,@RequestParam(value = "name") String name,@RequestParam(value = "posology") Integer posology,@RequestParam(value = "duration") Integer duration,  Model model) {
		Treatment t = this.treatments.findFirstById(Long.parseLong(id));
		Integer durationHoras = duration*24;
		t.setDuration(durationHoras);
		t.setName(name);
		t.setPosology(posology);
		
		/*Fecha expiracion*/
		Date dt = t.getActivationDate();
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.add(Calendar.DATE, duration);
        dt = c.getTime();
        t.setExpirationDate(dt);
		
		this.treatments.updateTreatment(t);
		logger.warn("Se ha actualizado el tratamiento solicitado");
		return "redirect:/user/treatments";
	}

	@RequestMapping(value = { "/user/createTreatment" }, method = { RequestMethod.POST, RequestMethod.PUT,  RequestMethod.GET})
	public String createTreatment(@RequestParam(value = "name") String name ,@RequestParam(value = "duration") Integer duration, @RequestParam(value = "posology") Integer posology, @RequestParam(value = "disease") Long disease,  Model model) {
		Disease d = this.disease.readSelectedDisease(disease);
		Integer durationHoras=duration*24;
		Treatment t = new Treatment(name, posology, durationHoras, d);
		User u = this.user.userLogged();
		t.setOwnerUser(u);
		
		State s = this.state.findFirstById(1L);
		t.setTreatmentState(s);
		
		Date date= new Date();
		t.setActivationDate(date);
		
		Date dt = t.getActivationDate();
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.add(Calendar.DATE, duration);
        dt = c.getTime();
        t.setExpirationDate(dt);
		this.treatments.createTreatment(t);
		logger.warn("Se ha cargado un nuevo tratamiento para el usuario " + u.getId() + " asociado a la enfermedad " + d.getName());
		return "redirect:/user/treatments";
	}
	
	@RequestMapping(value = { "/user/activeTreatment" }, method = { RequestMethod.POST, RequestMethod.PUT})
	public String activeOrDeactiveTreatment(@RequestParam(value = "idUpdate") String id,  Model model) {
		Treatment t = this.treatments.findFirstById(Long.parseLong(id));
		State state=null;
		if (t.getTreatmentState().getId()==1) {
			state = this.state.findFirstById(2L);
			logger.warn("Se ha desactivado un tratamiento");
			
		}else if (t.getTreatmentState().getId()==2) {
			state = this.state.findFirstById(1L);
			logger.warn("Se ha activado un tratamiento");
		}
		t.setTreatmentState(state);
		this.treatments.updateTreatment(t);
		return "redirect:/user/treatments";
	}

	
}
