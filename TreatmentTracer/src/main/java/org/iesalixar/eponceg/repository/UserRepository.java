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
	
	//Encuentra un usuario según su id
	public User findFirstById(Long id);
	
	//Método que muestra una lista de usuarios que tengan el cuidador que se pasa por parámetro
	public List<User> findByCareer(User user);
	
	//Método que borra el usuario con la Id que se pasa por parámetro
	public void deleteById(Long id);
	
	//Muestra todos los usuarios según su estado y su rol
	public List<User> findAllByRoleInAndState(Set<Role> roles, State state);
	
	//Muestra todos los usuarios con un rol determinado
	public List<User> findAllByRoleIn(Set<Role> roles);
	
	//Muestra todos los usuarios a cargo del cuidador que se pasa por parámetro ordenados por nombre descendente
	public List<User> findByCareerOrderByNameDesc(User u);
	
	//Muestra los usuarios a cargo del cuidador que se pasa por parámetro ordenado por email
	public List<User> findByCareerOrderByEmail(User u);
	
	//Muestra todos los usuarios a cargo del cuidador que se pasa por parámetro ordenados por nombre ascendente
	public List<User> findByCareerOrderByNameAsc (User u);
	
	//Muestra todos los usuarios a cargo del cuidado que se pasa por parámetro y cuyo nombre coincide con el pasado por parámetro
	public List<User> findByCareerAndName(User u, String name);
	
	
}
