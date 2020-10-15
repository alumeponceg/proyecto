package org.iesalixar.eponceg.repository;

import java.util.Optional;

import org.iesalixar.eponceg.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	//Método para el login, buscar un usuario en función de su email y su contraseña.
	public Optional<User> findByEmail(String email);	
	//Método para registrar un nuevo usuario en la base de datos.
	@SuppressWarnings("unchecked")
	public User save(User user);
	
	public User findFirstById(Long id);
	
	
}
