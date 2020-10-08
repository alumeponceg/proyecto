package org.iesalixar.eponceg.repository;

import java.util.List;
import java.util.Optional;

import org.iesalixar.eponceg.model.Disease;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiseaseRepository extends JpaRepository<Disease, Long> {

	public Optional<Disease> findById(Long id);
	
	public List<Disease> findByUser(Long id);
}
