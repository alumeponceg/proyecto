package org.iesalixar.eponceg.repository;

import java.util.Date;
import java.util.List;

import org.iesalixar.eponceg.model.MedicalAppointment;
import org.iesalixar.eponceg.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalAppointmentRepository extends JpaRepository<MedicalAppointment, Long> {

	//Método que encuentra las citas médicas de un usuario
	public List<MedicalAppointment> findByUserId(User user);
	
	//Método que encuentra las citas médicas de un usuario entre dos fechas
	public List<MedicalAppointment> findByUserIdAndAppointmentDateBetween (User id, Date startDate, Date finishDate);
	
	//Método para borrar una cita
	public void deleteById(Long id);
	
	//Método para añadir una cita a la base de datos
	@SuppressWarnings("unchecked")
	public MedicalAppointment save(MedicalAppointment medicalAppointent);
}
