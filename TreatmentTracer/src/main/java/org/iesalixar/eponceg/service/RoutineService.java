package org.iesalixar.eponceg.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.iesalixar.eponceg.model.Disease;
import org.iesalixar.eponceg.model.Measurement;
import org.iesalixar.eponceg.model.Routine;
import org.iesalixar.eponceg.model.User;
import org.iesalixar.eponceg.repository.RoutineRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoutineService {

	@Autowired
	private RoutineRepository routines;
	
	@Autowired
	private MeasurementService measurements;
	
	final static Logger logger = LoggerFactory.getLogger(RoutineService.class);
	
	public List<Routine> ListForAnUser(User user){
		return this.routines.findByOwnerUser(user);
	}
	
	public List<Routine> ListForAnUserOrderByName(User user){
		return this.routines.findByOwnerUserOrderByName(user);
	}
	
	public List<Routine> ListForAnUserOrderByNameDesc (User user){
		return this.routines.findByOwnerUserOrderByNameDesc(user);
	}
	
	public List<Routine> ListForAnUserOrderByDate (User user){
		return this.routines.findByOwnerUserOrderByActivationDate(user);
	}
	
	public List<Routine> ListForAnUserOrderByDisease( User user){
		return this.routines.findByOwnerUserOrderByDisease(user);
	}
	
	public List<Routine> ListForAnUserAndDisease( User user, Disease disease){
		return this.routines.findByOwnerUserAndDisease(user, disease);
	}
	
	public Routine createRoutine(Routine routine) {
		return this.routines.save(routine);
	}
	
	public Routine findFirstById(Long id) {
		return this.routines.findFirstById(id);
	}
	
	public void deleteRoutine(Routine r) {
		
		Set<Measurement> measurements = r.getMeasurements();
		
		for (Measurement m : measurements) {
			this.measurements.removeMeasurement(m.getId());
		}
		
		logger.warn("Se han eliminado todas las mediciones asociadas a la rutina " + r.getId());
		this.routines.delete(r);
	}
	
	public void updateRoutine(Routine routine) {
		this.routines.save(routine);
	}
	
	public List<Routine> findAllByExpirationDateLessThan(){
		Date date = new Date();
		return this.routines.findAllByExpirationDateLessThan(date);
	}

	public List<Routine> findAllByExpirationDateBetween() {
		Date dt = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.add(Calendar.DATE, 1);
        dt = c.getTime();
        Date start= new Date();
		
		return this.routines.findAllByExpirationDateBetween(start, dt);
	}
}
