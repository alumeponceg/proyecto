package org.iesalixar.eponceg.service;

import org.iesalixar.eponceg.repository.MeasurementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MeasurementService {

	@Autowired
	private MeasurementRepository measurements;
}
