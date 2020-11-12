package org.iesalixar.eponceg.repository;

import java.util.List;
import java.util.Set;

import org.iesalixar.eponceg.model.Disease;
import org.iesalixar.eponceg.model.State;
import org.iesalixar.eponceg.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiseaseRepository extends JpaRepository<Disease, Long> {

	//Encuentra las enfermedades segun su id
	public Disease findFirstById(Long id);
	
	//Encuentra las enfermedades de un usuario
	public List<Disease> findByUsersIn(Set<User> users);
	
	@SuppressWarnings("unchecked")
	public Disease save(Disease d);
	
	public List<Disease> findAll();
	
	public Disease findFirstByUsersIn(Set<User> users);
	
	public List<Disease> findByState(State state);
	
	public void deleteById(Long id);
}
