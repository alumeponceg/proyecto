package org.iesalixar.eponceg.repository;

import java.util.List;

import org.iesalixar.eponceg.model.Measurement;
import org.iesalixar.eponceg.model.RelationMeasurementRoutine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeasurementRepository extends JpaRepository<Measurement,RelationMeasurementRoutine>{
	
	public List<Measurement> findByRoutine(Long id);

}
