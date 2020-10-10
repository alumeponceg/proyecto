package org.iesalixar.eponceg.service;

import org.iesalixar.eponceg.repository.TreatmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TreatmentService {

	@Autowired
	private TreatmentRepository treatments;
}
