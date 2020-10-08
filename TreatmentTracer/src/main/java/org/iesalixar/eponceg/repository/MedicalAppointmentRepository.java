package org.iesalixar.eponceg.repository;

import java.util.Date;
import java.util.List;

import org.iesalixar.eponceg.model.MedicalAppointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalAppointmentRepository extends JpaRepository<MedicalAppointment, Long> {

	public List<MedicalAppointment> findByUserId(Long id);
	
	public List<MedicalAppointment> findByUserIdAndAppointmentDateBetween (Long id, Date startDate, Date finishDate);
}
