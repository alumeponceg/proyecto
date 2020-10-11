package org.iesalixar.eponceg.service;

import java.util.List;

import org.iesalixar.eponceg.model.Disease;
import org.iesalixar.eponceg.model.Treatment;
import org.iesalixar.eponceg.model.User;
import org.iesalixar.eponceg.repository.TreatmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TreatmentService {

	@Autowired
	private TreatmentRepository treatments;
	
	public List<Treatment> listTreatmentsForAnUser (User u){
		return this.treatments.findByOwnerUser(u);
	}
	
	public List<Treatment> listTreatmentsOrderByName(User u){
		return this.treatments.findByOwnerUserOrderByName(u);
	}
	
	public List<Treatment> listTreatmentsOrderByNameDesc(User u){
		return this.treatments.findByOwnerUserOrderByNameDesc(u);
	}
	
	public List<Treatment> listTreatmentsOrderByDate(User u){
		return this.treatments.findByOwnerUserOrderByActivationDate(u);
	}
	
	public List<Treatment> listTreatmentsForAnUserAndADisease(User u, Disease d){
		return this.treatments.findByOwnerUserAndDisease(u, d);
	}
	
	public Treatment createTreatment(Treatment treatment) {
		return this.treatments.save(treatment);
	}
	
	public void deleteTreatment(Long id) {
		this.treatments.deleteById(id);
	}
	
	public void updateTreatment(Treatment treatment) {
		this.treatments.save(treatment);
	}
	
	
}
