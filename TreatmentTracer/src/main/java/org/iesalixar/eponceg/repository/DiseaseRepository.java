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
	
	//Crea una nueva enfermedad en la aplicación
	@SuppressWarnings("unchecked")
	public Disease save(Disease d);
	
	//Lista todas las enfermedades existentes en la base de datos
	public List<Disease> findAll();
	
	//Encuentra la primera enfermedad de un usuario en una lista de usuarios
	public Disease findFirstByUsersIn(Set<User> users);
	
	//Muestra las enfermedades según su estado
	public List<Disease> findByState(State state);
	
	//Borra la enfermedad cuya id se pasa por parámetros
	public void deleteById(Long id);
}
