package org.iesalixar.eponceg.service;

import java.util.List;

import org.iesalixar.eponceg.model.Measurement;
import org.iesalixar.eponceg.repository.MeasurementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MeasurementService {

	@Autowired
	private MeasurementRepository measurements;
	
	public List<Measurement> ListMeasurementsForARoutine(Long id){
		return this.measurements.findByRoutine(id);
	}
	
	public Measurement createMeasurement(Measurement measurement) {
		return this.measurements.save(measurement);
	}
	
	public void updateMeasurement (Measurement measurement) {
		this.measurements.save(measurement);
	}
}
