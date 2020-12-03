package org.iesalixar.eponceg.repository;

import java.util.List;

import org.iesalixar.eponceg.model.Measurement;
import org.iesalixar.eponceg.model.Routine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeasurementRepository extends JpaRepository<Measurement,Long>{
	
	//Método para obtener una medición segun su id
	public Measurement findFirstById(Long id);
	
	//Método para encontrar las mediciones de una rutina
	public List<Measurement> findByRoutineOrderByDateAsc(Routine r);
	
	//Método para añadir en la base de datos una nueva medición
	@SuppressWarnings("unchecked")
	public Measurement save(Measurement measurement);
	
	//Método para borrar una medición
	public void deleteById(Long id);
	
	//Método que encuentra la primera medición de una rutina. 
	//Lo usamos para poder autoasignar la unidad de medida cuando ya existe alguna medición asociada a esa rutina
	public Measurement findFirstByRoutine(Routine r);

}
