package org.iesalixar.eponceg.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.iesalixar.eponceg.model.Disease;
import org.iesalixar.eponceg.model.Measurement;
import org.iesalixar.eponceg.model.Routine;
import org.iesalixar.eponceg.model.State;
import org.iesalixar.eponceg.model.User;
import org.iesalixar.eponceg.repository.UserRepository;
import org.iesalixar.eponceg.service.DiseaseService;
import org.iesalixar.eponceg.service.MeasurementService;
import org.iesalixar.eponceg.service.RoutineService;
import org.iesalixar.eponceg.service.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RoutineController {

	@Autowired
	private RoutineService routines;
	
	@Autowired
	private UserRepository users;
		
	@Autowired
	private StateService state;
	
	@Autowired
	private DiseaseService disease;
	
	@Autowired
	private MeasurementService measurements;
	
	
	@RequestMapping(value ={"/user/routines"},  method = { RequestMethod.POST, RequestMethod.GET})
	public String routinesOrder(@RequestParam(value = "order", defaultValue = "null") String order, Model model) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails userDetails = null;
		if (principal instanceof UserDetails) {
		  userDetails = (UserDetails) principal;
		}
		String email = userDetails.getUsername();
		Optional<User> u = this.users.findByEmail(email);
		Set<User> users = new HashSet<>();
		users.add(u.get());
		
		List<Routine> routines = new ArrayList<>();
		switch (order) {
		case "1":
			routines = this.routines.ListForAnUserOrderByName(u.get());
			for (Routine r: routines) {
				r.setDuration(r.getDuration()/24);
			}
			model.addAttribute("routines", routines);
			model.addAttribute("diseases", this.disease.readDiseases(users));
			break;
		case "2":
			routines = this.routines.ListForAnUserOrderByNameDesc(u.get());
			for (Routine r: routines) {
				r.setDuration(r.getDuration()/24);
			}
			model.addAttribute("routines", routines);
			model.addAttribute("diseases", this.disease.readDiseases(users));
			break;
		case "3":
			routines = this.routines.ListForAnUserOrderByDate(u.get());
			for (Routine r: routines) {
				r.setDuration(r.getDuration()/24);
			}
			model.addAttribute("routines", routines);
			model.addAttribute("diseases", this.disease.readDiseases(users));
			break;
		case "4":
			routines = this.routines.ListForAnUserOrderByDisease(u.get());
			for (Routine r: routines) {
				r.setDuration(r.getDuration()/24);
			}
			model.addAttribute("routines", routines);
			model.addAttribute("diseases", this.disease.readDiseases(users));
			break;
		default:
			routines = this.routines.ListForAnUser(u.get());
			for (Routine r: routines) {
				r.setDuration(r.getDuration()/24);
			}
			model.addAttribute("routines", routines);
			model.addAttribute("diseases", this.disease.readDiseases(users));
			break;
		}
		
		return "routines";
	}

	@RequestMapping(value = { "/user/removeRoutine" }, method = { RequestMethod.POST, RequestMethod.DELETE })
	public String deleteRoutine(@RequestParam(value = "id") String id, Model model) {
		Routine r = this.routines.findFirstById(Long.parseLong(id));
		Set<Measurement> measurements = r.getMeasurements();
		
		for (Measurement m : measurements) {
			this.measurements.removeMeasurement(m.getId());
		}
		
		this.routines.deleteRoutine(r);
		return "redirect:/user/routines";
	}
	
	@RequestMapping(value = { "/user/updateRoutine" }, method = { RequestMethod.POST, RequestMethod.PUT,  RequestMethod.GET})
	public String updateRoutine(@RequestParam(value = "id") String id,@RequestParam(value = "name") String name,@RequestParam(value = "posology") Integer posology,@RequestParam(value = "duration") Integer duration,  Model model) {
		Routine r = this.routines.findFirstById(Long.parseLong(id));
		Integer durationHoras=duration*24;
		r.setDuration(durationHoras);
		r.setName(name);
		r.setPosology(posology);
		
		/*Fecha expiracion*/
		Date dt = r.getActivationDate();
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.add(Calendar.DATE, duration);
        dt = c.getTime();
        r.setExpirationDate(dt);
		
		this.routines.updateRoutine(r);
		return "redirect:/user/routines";
	}

	@RequestMapping(value = { "/user/createRoutine" }, method = { RequestMethod.POST, RequestMethod.PUT,  RequestMethod.GET})
	public String createRoutine(@RequestParam(value = "name") String name ,@RequestParam(value = "duration") Integer duration, @RequestParam(value = "posology") Integer posology, @RequestParam(value = "disease") Long disease,  Model model) {
		Disease d = this.disease.readSelectedDisease(disease);
		Integer durationHoras=duration*24;
		Routine r = new Routine(name, posology, durationHoras, d);
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails userDetails = null;
		if (principal instanceof UserDetails) {
		  userDetails = (UserDetails) principal;
		}
		String email = userDetails.getUsername();
		Optional<User> u = this.users.findByEmail(email);
		r.setOwnerUser(u.get());
		
		State s = this.state.findFirstById(1L);
		r.setRoutineState(s);
		
		Date date= new Date();
		r.setActivationDate(date);
		
		/*Fecha expiracion*/
		Date dt = r.getActivationDate();
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.add(Calendar.DATE, duration);
        dt = c.getTime();
        r.setExpirationDate(dt);
		
		this.routines.createRoutine(r);
		return "redirect:/user/routines";
	}
	
	@RequestMapping(value = { "/user/activeRoutine" }, method = { RequestMethod.POST, RequestMethod.PUT})
	public String activeOrDeactiveRoutine(@RequestParam(value = "idUpdate") String id,  Model model) {
		Routine r = this.routines.findFirstById(Long.parseLong(id));
		State state=null;
		if (r.getRoutineState().getId()==1) {
			state = this.state.findFirstById(2L);
			
		}else if (r.getRoutineState().getId()==2) {
			state = this.state.findFirstById(1L);
		}
		r.setRoutineState(state);
		this.routines.updateRoutine(r);
		return "redirect:/user/routines";
	}

	
}
