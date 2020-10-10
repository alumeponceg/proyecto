package org.iesalixar.eponceg.service;

import java.util.List;

import org.iesalixar.eponceg.model.Routine;
import org.iesalixar.eponceg.repository.RoutineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoutineService {

	@Autowired
	private RoutineRepository routines;
	
	public List<Routine> ListForAnUser(Long userId){
		return this.routines.findByOwnerUser(userId);
	}
	
	public List<Routine> ListForAnUserOrderByName(Long userId){
		return this.routines.findByOwnerUserOrderByName(userId);
	}
	
	public List<Routine> ListForAnUserOrderByNameDesc (Long userId){
		return this.routines.findByOwnerUserOrderByNameDesc(userId);
	}
	
	public List<Routine> ListForAnUserOrderByDate (Long userId){
		return this.routines.findByOwnerUserOrderByActivationDate(userId);
	}
	
	public List<Routine> ListForAnUserAndADisease( Long userId, Long disease){
		return this.routines.findByOwnerUserAndDisease(userId, disease);
	}
	
	public Routine createRoutine(Routine routine) {
		return this.routines.save(routine);
	}
	
	public void deleteRoutine(Long id) {
		this.deleteRoutine(id);
	}
	
	public void updateRoutine(Routine routine) {
		this.routines.save(routine);
	}
}
