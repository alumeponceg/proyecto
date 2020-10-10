package org.iesalixar.eponceg.repository;

import java.util.List;

import org.iesalixar.eponceg.model.Disease;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiseaseRepository extends JpaRepository<Disease, Long> {

	//Encuentra las enfermedades segun su id
	public Disease findFirstById(Long id);
	
	//Encuentra las enfermedades de un usuario
	public List<Disease> findByUsers(Long id);
}
