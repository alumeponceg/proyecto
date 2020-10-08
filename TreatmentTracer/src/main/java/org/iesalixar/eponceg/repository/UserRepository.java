package org.iesalixar.eponceg.repository;

import java.util.List;

import org.iesalixar.eponceg.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	public List<User> findByEmailAndPassword(String email, String password);
	
}
