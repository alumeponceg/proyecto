package org.iesalixar.eponceg.repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.iesalixar.eponceg.model.Role;
import org.iesalixar.eponceg.model.State;
import org.iesalixar.eponceg.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	//Método para el login, buscar un usuario en función de su email y su contraseña.
	public Optional<User> findByEmail(String email);	
	//Método para registrar un nuevo usuario en la base de datos.
	@SuppressWarnings("unchecked")
	public User save(User user);
	
	public User findFirstById(Long id);
	
	public List<User> findByCareer(User user);
	
	public void deleteById(Long id);
	
	public List<User> findAllByRoleInAndState(Set<Role> roles, State state);
	
	public List<User> findAllByRoleIn(Set<Role> roles);
	
//	@Query("SELECT count(*), c.name, c.surname, c.email from user u inner join user c on c.id=u.career where u.career=4")
//	public String countByCarer(User carer);
}
