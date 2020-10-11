package org.iesalixar.eponceg.repository;

import java.util.List;

import org.iesalixar.eponceg.model.Disease;
import org.iesalixar.eponceg.model.Treatment;
import org.iesalixar.eponceg.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TreatmentRepository extends JpaRepository<Treatment, Long> {

	//Método que busca los tratamientos de un usuario
	public List<Treatment> findByOwnerUser(User user);
	
	//Método que buscar los tratamientos de un usuario ordenados por nombre
	public List<Treatment> findByOwnerUserOrderByName(User user);
	
	//Método que buscar los tratamientos de un usuario ordenados por nombre descendente
	public List<Treatment> findByOwnerUserOrderByNameDesc(User user);
	
	//Método que buscar los tratamientos de un usuario ordenados por la fecha de activación
	public List<Treatment> findByOwnerUserOrderByActivationDate(User user);
	
	//Método que buscar los tratamientos de un usuario y una enfermedad concreta
	public List<Treatment> findByOwnerUserAndDisease(User user, Disease disease);
	
	//Método que borra un tratamiento
	public void deleteById(Long id);
	
	//Método que añade un tratamiento a la base de datos
	@SuppressWarnings("unchecked")
	public Treatment save(Treatment treatment);
	
}
