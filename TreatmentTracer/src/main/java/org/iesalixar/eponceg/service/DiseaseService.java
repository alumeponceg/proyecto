package org.iesalixar.eponceg.service;

import java.util.List;

import org.iesalixar.eponceg.model.Disease;
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
	
	public List<Disease> ListAllDiseasesForAnUser(Long id){
		return this.disease.findByUsers(id);
	}
}
