package org.iesalixar.eponceg.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.iesalixar.eponceg.model.Disease;
import org.iesalixar.eponceg.model.Routine;
import org.iesalixar.eponceg.model.State;
import org.iesalixar.eponceg.model.User;
import org.iesalixar.eponceg.service.DiseaseService;
import org.iesalixar.eponceg.service.RoutineService;
import org.iesalixar.eponceg.service.StateService;
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
public class RoutineController {
	
	final static Logger logger = LoggerFactory.getLogger(RoutineController.class);

	@Autowired
	private RoutineService routines;

	@Autowired
	private UserService user;
		
	@Autowired
	private StateService state;
	
	@Autowired
	private DiseaseService disease;
	
	
	
	
	@RequestMapping(value ={"/user/routines"},  method = { RequestMethod.POST, RequestMethod.GET})
	public String routinesOrder(@RequestParam(value = "order", defaultValue = "null") String order, Model model) {
		User u = this.user.userLogged();
		Set<User> users = new HashSet<>();
		users.add(u);
		
		List<Routine> routines = new ArrayList<>();
		switch (order) {
		case "1":
			routines = this.routines.ListForAnUserOrderByName(u);
			for (Routine r: routines) {
				r.setDuration(r.getDuration()/24);
			}
			model.addAttribute("routines", routines);
			model.addAttribute("diseases", this.disease.readDiseases(users));
			logger.warn("Las rutinas se han mostrado ordenadas por nombre");
			break;
		case "2":
			routines = this.routines.ListForAnUserOrderByNameDesc(u);
			for (Routine r: routines) {
				r.setDuration(r.getDuration()/24);
			}
			model.addAttribute("routines", routines);
			model.addAttribute("diseases", this.disease.readDiseases(users));
			logger.warn("Las rutinas se han mostrado ordenadas por nombre en orden descendente");
			break;
		case "3":
			routines = this.routines.ListForAnUserOrderByDate(u);
			for (Routine r: routines) {
				r.setDuration(r.getDuration()/24);
			}
			model.addAttribute("routines", routines);
			model.addAttribute("diseases", this.disease.readDiseases(users));
			logger.warn("Las rutinas se han mostrado ordenadas por fecha de activación");
			break;
		case "4":
			routines = this.routines.ListForAnUserOrderByDisease(u);
			for (Routine r: routines) {
				r.setDuration(r.getDuration()/24);
			}
			model.addAttribute("routines", routines);
			model.addAttribute("diseases", this.disease.readDiseases(users));
			logger.warn("Las rutinas se han mostrado ordenadas por enfermedad asociada");
			break;
		default:
			routines = this.routines.ListForAnUser(u);
			for (Routine r: routines) {
				r.setDuration(r.getDuration()/24);
			}
			model.addAttribute("routines", routines);
			model.addAttribute("diseases", this.disease.readDiseases(users));
			logger.warn("Las rutinas se han mostrado en el orden predeterminado");
			break;
		}
		
		return "routines";
	}

	@RequestMapping(value = { "/user/removeRoutine" }, method = { RequestMethod.POST, RequestMethod.DELETE })
	public String deleteRoutine(@RequestParam(value = "id") String id, Model model) {
		Routine r = this.routines.findFirstById(Long.parseLong(id));
		this.routines.deleteRoutine(r);
		logger.warn("Se ha eliminado la rutina " + r.getName() + " y con ella todas sus mediciones asociadas.");
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
		
		logger.warn("Se ha actualizado la rutina " + r.getName());
		return "redirect:/user/routines";
	}

	@RequestMapping(value = { "/user/createRoutine" }, method = { RequestMethod.POST, RequestMethod.PUT,  RequestMethod.GET})
	public String createRoutine(@RequestParam(value = "name") String name ,@RequestParam(value = "duration") Integer duration, @RequestParam(value = "posology") Integer posology, @RequestParam(value = "disease") Long disease,  Model model) {
		Disease d = this.disease.readSelectedDisease(disease);
		Integer durationHoras=duration*24;
		Routine r = new Routine(name, posology, durationHoras, d);
		
		User u = this.user.userLogged();
		r.setOwnerUser(u);
		
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
		logger.warn("El usuario " + u.getId() + " ha registrado una nueva rutina en su perfil");
		return "redirect:/user/routines";
	}
	
	@RequestMapping(value = { "/user/activeRoutine" }, method = { RequestMethod.POST, RequestMethod.PUT})
	public String activeOrDeactiveRoutine(@RequestParam(value = "idUpdate") String id,  Model model) {
		Routine r = this.routines.findFirstById(Long.parseLong(id));
		State state=null;
		if (r.getRoutineState().getId()==1) {
			state = this.state.findFirstById(2L);
			logger.warn("Se ha desactivado una rutina");
			
		}else if (r.getRoutineState().getId()==2) {
			state = this.state.findFirstById(1L);
			logger.warn("Se ha activado una rutina");
		}
		r.setRoutineState(state);
		this.routines.updateRoutine(r);
		return "redirect:/user/routines";
	}

	
}
