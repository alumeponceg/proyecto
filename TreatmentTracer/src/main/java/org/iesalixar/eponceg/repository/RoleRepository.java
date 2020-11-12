package org.iesalixar.eponceg.repository;

import org.iesalixar.eponceg.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	
	public Role findFirstById(Long id );

}
