package org.iesalixar.eponceg.service;

import org.iesalixar.eponceg.repository.MedicalAppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicalAppointmentService {

	@Autowired
	private MedicalAppointmentRepository medicalAppointment;
}
