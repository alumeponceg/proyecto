package org.iesalixar.eponceg.repository;

import java.util.List;

import org.iesalixar.eponceg.model.Disease;
import org.iesalixar.eponceg.model.Routine;
import org.iesalixar.eponceg.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoutineRepository extends JpaRepository<Routine, Long> {

	//Método que buscar las rutinas de un usuario
	public List<Routine> findByOwnerUser(User user);
	
	//Método que buscar las rutinas de un usuario ordenados por nombre
	public List<Routine> findByOwnerUserOrderByName(User user);
	
	//Método que buscar las rutinas de un usuario ordenados por nombre desc
	public List<Routine> findByOwnerUserOrderByNameDesc(User user);
	
	//Método que buscar las rutinas de un usuario ordenados por fecha de activación
	public List<Routine> findByOwnerUserOrderByActivationDate(User user);
	
	//Método que buscar las rutinas de un usuario ordenado por enfermedades
	public List<Routine> findByOwnerUserOrderByDisease(User user);
	
	//Método para buscar las rutinas de un usuario y enfermedad concreta
	public List<Routine> findByOwnerUserAndDisease(User user, Disease disease);
	
	//Método para añadir una rutina a la base de datos
	@SuppressWarnings("unchecked")
	public Routine save(Routine routine);
	
	//Método para borrar una rutina
	public void delete(Routine r);
	
	public Routine findFirstById(Long id);
	
}
