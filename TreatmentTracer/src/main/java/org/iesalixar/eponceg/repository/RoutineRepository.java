package org.iesalixar.eponceg.repository;

import java.util.List;

import org.iesalixar.eponceg.model.Routine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoutineRepository extends JpaRepository<Routine, Long> {

	//Método que buscar las rutinas de un usuario
	public List<Routine> findByOwnerUser(Long user);
	
	//Método que buscar las rutinas de un usuario ordenados por nombre
	public List<Routine> findByOwnerUserOrderByName(Long user);
	
	//Método que buscar las rutinas de un usuario ordenados por nombre desc
	public List<Routine> findByOwnerUserOrderByNameDesc(Long user);
	
	//Método que buscar las rutinas de un usuario ordenados por fecha de activación
	public List<Routine> findByOwnerUserOrderByActivationDate(Long user);
	
	//Método que buscar las rutinas de un usuario y enfermedad concreta
	public List<Routine> findByOwnerUserAndDisease(Long user, Long disease);
	
	//Método para añadir una rutina a la base de datos
	@SuppressWarnings("unchecked")
	public Routine save(Routine routine);
	
	//Método para borrar una rutina
	public void delete(Long id);
	
}
