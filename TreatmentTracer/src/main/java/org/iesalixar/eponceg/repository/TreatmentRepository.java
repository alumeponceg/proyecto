package org.iesalixar.eponceg.repository;

import java.util.List;

import org.iesalixar.eponceg.model.Treatment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TreatmentRepository extends JpaRepository<Treatment, Long> {

	public List<Treatment> findByOwnerUser(Long user);
	public List<Treatment> findByOwnerUserOrderByName(Long user);
	public List<Treatment> findByOwnerUserOrderByNameDesc(Long user);
	public List<Treatment> findByOwnerUserOrderByActivationDate(Long user);
	public List<Treatment> findByOwnerUserAndDisease(Long user, Long disease);
}
