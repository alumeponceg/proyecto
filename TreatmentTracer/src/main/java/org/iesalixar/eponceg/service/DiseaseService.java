package org.iesalixar.eponceg.service;

import java.util.List;
import java.util.Set;

import org.iesalixar.eponceg.model.Disease;
import org.iesalixar.eponceg.model.User;
import org.iesalixar.eponceg.repository.DiseaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiseaseService {

	@Autowired
	private DiseaseRepository disease;
	
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
}
