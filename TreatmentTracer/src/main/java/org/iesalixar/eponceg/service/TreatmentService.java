package org.iesalixar.eponceg.service;

import java.util.Calendar;
import java.util.Date;
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
	
	public List<Treatment> listTreatmentsForAnUserOrderByDisease(User u){
		return this.treatments.findByOwnerUserOrderByDisease(u);
	}
	
	public List<Treatment> findByOwnerUserAndDisease(User user, Disease disease){
		return this.treatments.findByOwnerUserAndDisease(user, disease);
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
	
	public Treatment findFirstById(Long id) {
		return this.treatments.findFirstById(id);
	}
	
	public List<Treatment> findAllByExpirationDateLessThan(){
		Date date = new Date();
		return this.treatments.findAllByExpirationDateLessThan(date);
	}
	
	public List<Treatment> findAllByExpirationDateBetween() {
		Date dt = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.add(Calendar.DATE, 1);
        dt = c.getTime();
        Date start= new Date();
		return this.treatments.findAllByExpirationDateBetween(start, dt);
	}
}
