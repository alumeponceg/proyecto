package org.iesalixar.eponceg.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import org.iesalixar.eponceg.model.Measurement;
import org.iesalixar.eponceg.model.Routine;
import org.iesalixar.eponceg.service.MeasurementService;
import org.iesalixar.eponceg.service.RoutineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MeasurementController {

	@Autowired
	private MeasurementService measurements;
	
	@Autowired
	private RoutineService routines;

	private String chargeRoutine;
	
	@RequestMapping(value ={"/user/measurements"},  method = { RequestMethod.POST, RequestMethod.GET})
	public String measurements(@RequestParam(value = "routineId", defaultValue ="null") String routineId, Model model) {
		if(routineId.equals("null")) {
			routineId=chargeRoutine;
		}
		Routine r = this.routines.findFirstById(Long.parseLong(routineId));
		
		model.addAttribute("measurements", this.measurements.ListMeasurementsForARoutine(r));
		model.addAttribute("routine", r);
		return "measurements";
	}

	
	@SuppressWarnings("deprecation")
	@RequestMapping(value = { "/user/updateMeasurement" }, method = { RequestMethod.POST, RequestMethod.PUT,  RequestMethod.GET})
	public String updateMeasurement(@RequestParam(value = "id") String id,@RequestParam(value = "value") String value,@RequestParam(value = "date") String date,  Model model) throws ParseException {
		Measurement m = this.measurements.findFirstById(Long.parseLong(id));
		m.setValue(Float.parseFloat(value));
		 SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
	        
	        Date    fechaDate = formato.parse(date);
	        fechaDate.setDate(fechaDate.getDate()+1);
	       
		m.setDate(fechaDate);
		this.measurements.updateMeasurement(m);
		chargeRoutine=m.getRoutine().getId()+"";
		return "redirect:/user/measurements";
	}

	@RequestMapping(value = { "/user/createMeasurement" }, method = { RequestMethod.POST, RequestMethod.PUT,  RequestMethod.GET})
	public String createMeasurement(@RequestParam(value = "value") String value ,@RequestParam(value = "unit" , defaultValue ="null") String unit, @RequestParam(value = "routineId") Long routineId,  Model model) {
		Routine rout = this.routines.findFirstById(routineId);
		Date date= new Date();
		if(unit.equals("null")) {
			Measurement meas =this.measurements.findFirstByRoutine(rout);
			unit= meas.getUnit();
		}
		Measurement m = new Measurement(unit, date, Float.parseFloat(value), rout);
		this.measurements.createMeasurement(m);
		Set<Measurement> mediciones = rout.getMeasurements();
		mediciones.add(m);
		rout.setMeasurements(mediciones);
		this.routines.updateRoutine(rout);		
		chargeRoutine=routineId+"";
		return "redirect:/user/measurements";
	}
	
}
