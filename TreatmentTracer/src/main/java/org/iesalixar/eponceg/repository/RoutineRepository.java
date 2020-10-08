package org.iesalixar.eponceg.repository;

import java.util.List;

import org.iesalixar.eponceg.model.Routine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoutineRepository extends JpaRepository<Routine, Long> {

	public List<Routine> findByOwnerUser(Long user);
	public List<Routine> findByOwnerUserOrderByName(Long user);
	public List<Routine> findByOwnerUserOrderByNameDesc(Long user);
	public List<Routine> findByOwnerUserOrderByActivationDate(Long user);
	public List<Routine> findByOwnerUserAndDisease(Long user, Long disease);
}
