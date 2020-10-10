package org.iesalixar.eponceg.repository;

import java.util.List;

import org.iesalixar.eponceg.model.Treatment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TreatmentRepository extends JpaRepository<Treatment, Long> {

	//Método que busca los tratamientos de un usuario
	public List<Treatment> findByOwnerUser(Long user);
	
	//Método que buscar los tratamientos de un usuario ordenados por nombre
	public List<Treatment> findByOwnerUserOrderByName(Long user);
	
	//Método que buscar los tratamientos de un usuario ordenados por nombre descendente
	public List<Treatment> findByOwnerUserOrderByNameDesc(Long user);
	
	//Método que buscar los tratamientos de un usuario ordenados por la fecha de activación
	public List<Treatment> findByOwnerUserOrderByActivationDate(Long user);
	
	//Método que buscar los tratamientos de un usuario y una enfermedad concreta
	public List<Treatment> findByOwnerUserAndDisease(Long user, Long disease);
	
	//Método que borra un tratamiento
	public void delete(Long id);
	
	//Método que añade un tratamiento a la base de datos
	@SuppressWarnings("unchecked")
	public Treatment save(Treatment treatment);
	
}
