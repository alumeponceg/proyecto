package org.iesalixar.eponceg.repository;

import org.iesalixar.eponceg.model.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StateRepository extends JpaRepository<State, Long>{

	//Muestra un estado según su id
	public State findFirstById(Long id);
}
