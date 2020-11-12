package org.iesalixar.eponceg.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.iesalixar.eponceg.model.Disease;
import org.iesalixar.eponceg.model.Routine;
import org.iesalixar.eponceg.model.Treatment;
import org.iesalixar.eponceg.model.User;
import org.iesalixar.eponceg.repository.DiseaseRepository;
import org.iesalixar.eponceg.repository.StateRepository;
import org.iesalixar.eponceg.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiseaseService {

	@Autowired
	private DiseaseRepository disease;
	
	@Autowired
	private StateRepository state;
	
	@Autowired
	private TreatmentService treatments;
	
	@Autowired
	private RoutineService routines;
	
	@Autowired
	private UserRepository users;
	
	public Disease readSelectedDisease(Long id) {
		return this.disease.findFirstById(id);
	}
	
	public List<Disease> readDiseases(Set<User> users){
		
		return this.disease.findByUsersIn(users);
	}
	
	public Disease updateDisease(Disease d) {
		return this.disease.save(d);
	}
	
	public List<Disease> selectAllNotRepeat(Set<User> users){
		List<Disease> exists = this.disease.findByUsersIn(users);
		List<Disease> allDiseases = this.disease.findAll();
		
		allDiseases.removeAll(exists);
		
		return allDiseases;
	}
	
	public Disease findFirstByUsersIn(Set<User> users) {
		return this.disease.findFirstByUsersIn(users);
	}
	
	public List<Disease> listInactive(){
		return this.disease.findByState(this.state.findFirstById(2L));
	}
	
	public void deleteById(Long id) {
		this.disease.deleteById(id);
	}
	
	public List<Disease> listAllDisease(){
		return this.disease.findAll();
	}
	
	public void unlinkDisease(User user, Disease disease) {
		Set<User> users = new HashSet<>();
		users.add(user);
		Set<Disease> diseases = user.getDiseases();
		diseases.remove(disease);
		
		List<Treatment> treats = this.treatments.findByOwnerUserAndDisease(user, disease);
		for (Treatment t : treats) {
			this.treatments.deleteTreatment(t.getId());
		}
		
		List<Routine> routs = this.routines.ListForAnUserAndDisease(user, disease);
		
		for (Routine r : routs) {
			this.routines.deleteRoutine(r);
		}
		
		user.setDiseases(diseases);
		this.users.save(user);

		Set<User> usuarios = disease.getUsers();
		usuarios.remove(user);
		disease.setUsers(usuarios);
		this.updateDisease(disease);
	}
	
	public Disease createDisease(Disease d) {
		return this.disease.save(d);
	}
}
