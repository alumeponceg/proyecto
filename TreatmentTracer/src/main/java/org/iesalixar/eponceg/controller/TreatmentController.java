package org.iesalixar.eponceg.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.iesalixar.eponceg.model.Disease;
import org.iesalixar.eponceg.model.State;
import org.iesalixar.eponceg.model.Treatment;
import org.iesalixar.eponceg.model.User;
import org.iesalixar.eponceg.repository.UserRepository;
import org.iesalixar.eponceg.service.DiseaseService;
import org.iesalixar.eponceg.service.StateService;
import org.iesalixar.eponceg.service.TreatmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TreatmentController {

	@Autowired
	private UserRepository users;
	
	@Autowired
	private TreatmentService treatments;
	
	@Autowired
	private StateService state;
	
	@Autowired
	private DiseaseService disease;
	
	@RequestMapping(value ={"/treatments"},  method = { RequestMethod.POST, RequestMethod.GET})
	public String treatmentsOrder(@RequestParam(value = "order", defaultValue = "null") String order, Model model) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails userDetails = null;
		if (principal instanceof UserDetails) {
		  userDetails = (UserDetails) principal;
		}
		String email = userDetails.getUsername();
		Optional<User> u = this.users.findByEmail(email);
		Set<User> users = new HashSet<>();
		users.add(u.get());
		List<Treatment> treatments = new ArrayList<>();
		
		switch (order) {
		case "1":
			 treatments = this.treatments.listTreatmentsOrderByName(u.get());
			
			for(Treatment t : treatments) {
				t.setDuration(t.getDuration()/24);
			}
			model.addAttribute("treatments", treatments);
			model.addAttribute("diseases", this.disease.readDiseases(users));
			break;
		case "2":
			 treatments = this.treatments.listTreatmentsOrderByNameDesc(u.get());
				
				for(Treatment t : treatments) {
					t.setDuration(t.getDuration()/24);
				}
				model.addAttribute("treatments", treatments);
			model.addAttribute("diseases", this.disease.readDiseases(users));
			break;
		case "3":
			 treatments = this.treatments.listTreatmentsOrderByDate(u.get());
				
				for(Treatment t : treatments) {
					t.setDuration(t.getDuration()/24);
				}
				model.addAttribute("treatments", treatments);
			model.addAttribute("diseases", this.disease.readDiseases(users));
			break;
		case "4":
			 treatments =  this.treatments.listTreatmentsForAnUserOrderByDisease(u.get());
				
				for(Treatment t : treatments) {
					t.setDuration(t.getDuration()/24);
				}
				model.addAttribute("treatments", treatments);
			model.addAttribute("diseases", this.disease.readDiseases(users));
			break;
		default:
			 treatments = this.treatments.listTreatmentsForAnUser(u.get());
				
				for(Treatment t : treatments) {
					t.setDuration(t.getDuration()/24);
				}
				model.addAttribute("treatments", treatments);
			model.addAttribute("diseases", this.disease.readDiseases(users));
			break;
		}
		
		return "treatments";
	}

	@RequestMapping(value = { "/removeTreatment" }, method = { RequestMethod.POST, RequestMethod.DELETE })
	public String deleteTreatment(@RequestParam(value = "id") String id, Model model) {
		this.treatments.deleteTreatment(Long.parseLong(id));
		return "redirect:/treatments";
	}
	
	@RequestMapping(value = { "/updateTreatment" }, method = { RequestMethod.POST, RequestMethod.PUT,  RequestMethod.GET})
	public String updateTreatment(@RequestParam(value = "id") String id,@RequestParam(value = "name") String name,@RequestParam(value = "posology") Integer posology,@RequestParam(value = "duration") Integer duration,  Model model) {
		Treatment t = this.treatments.findFirstById(Long.parseLong(id));
		duration = duration*24;
		t.setDuration(duration);
		t.setName(name);
		t.setPosology(posology);
		this.treatments.updateTreatment(t);
		return "redirect:/treatments";
	}

	@RequestMapping(value = { "/createTreatment" }, method = { RequestMethod.POST, RequestMethod.PUT,  RequestMethod.GET})
	public String createTreatment(@RequestParam(value = "name") String name ,@RequestParam(value = "duration") Integer duration, @RequestParam(value = "posology") Integer posology, @RequestParam(value = "disease") Long disease,  Model model) {
		Disease d = this.disease.readSelectedDisease(disease);
		duration=duration*24;
		Treatment t = new Treatment(name, posology, duration, d);
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails userDetails = null;
		if (principal instanceof UserDetails) {
		  userDetails = (UserDetails) principal;
		}
		String email = userDetails.getUsername();
		Optional<User> u = this.users.findByEmail(email);
		t.setOwnerUser(u.get());
		
		State s = this.state.findFirstById(1L);
		t.setTreatmentState(s);
		
		Date date= new Date();
		t.setActivationDate(date);
		this.treatments.createTreatment(t);
		return "redirect:/treatments";
	}
	
	@RequestMapping(value = { "/activeTreatment" }, method = { RequestMethod.POST, RequestMethod.PUT})
	public String activeOrDeactiveTreatment(@RequestParam(value = "idUpdate") String id,  Model model) {
		Treatment t = this.treatments.findFirstById(Long.parseLong(id));
		State state=null;
		if (t.getTreatmentState().getId()==1) {
			state = this.state.findFirstById(2L);
			
		}else if (t.getTreatmentState().getId()==2) {
			state = this.state.findFirstById(1L);
		}
		t.setTreatmentState(state);
		this.treatments.updateTreatment(t);
		return "redirect:/treatments";
	}

	
}
