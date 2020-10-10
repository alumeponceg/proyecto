package org.iesalixar.eponceg.service;

import java.util.List;

import org.iesalixar.eponceg.model.Treatment;
import org.iesalixar.eponceg.repository.TreatmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TreatmentService {

	@Autowired
	private TreatmentRepository treatments;
	
	public List<Treatment> listTreatmentsForAnUser (Long userId){
		return this.treatments.findByOwnerUser(userId);
	}
	
	public List<Treatment> listTreatmentsOrderByName(Long userId){
		return this.treatments.findByOwnerUserOrderByName(userId);
	}
	
	public List<Treatment> listTreatmentsOrderByNameDesc(Long userId){
		return this.treatments.findByOwnerUserOrderByNameDesc(userId);
	}
	
	public List<Treatment> listTreatmentsOrderByDate(Long userId){
		return this.treatments.findByOwnerUserOrderByActivationDate(userId);
	}
	
	public List<Treatment> listTreatmentsForAnUserAndADisease(Long userId, Long disease){
		return this.treatments.findByOwnerUserAndDisease(userId, disease);
	}
	
	public Treatment createTreatment(Treatment treatment) {
		return this.treatments.save(treatment);
	}
	
	public void deleteTreatment(Long id) {
		this.treatments.delete(id);
	}
	
	public void updateTreatment(Treatment treatment) {
		this.treatments.save(treatment);
	}
	
	
}
