package org.iesalixar.eponceg.service;

import java.util.Date;
import java.util.List;

import org.iesalixar.eponceg.model.MedicalAppointment;
import org.iesalixar.eponceg.repository.MedicalAppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicalAppointmentService {

	@Autowired
	private MedicalAppointmentRepository medicalAppointment;
	
	public List<MedicalAppointment> ListForAnUser(Long userId){
		return this.medicalAppointment.findByUserId(userId);
	}
	
	public List<MedicalAppointment> ListForTheNextMonth(Long UserId, Date startDate, Date finishDate){
		return this.medicalAppointment.findByUserIdAndAppointmentDateBetween(UserId, startDate, finishDate);
	}
	
	public void delete(Long id) {
		this.medicalAppointment.deleteById(id);
	}
	
	public MedicalAppointment createMedicalAppointment(MedicalAppointment medicalAppointment) {
		return this.medicalAppointment.save(medicalAppointment);
	}
	
	public void updateMedicalAppointment (MedicalAppointment medicalAppointment) {
		this.medicalAppointment.save(medicalAppointment);
	}
}
