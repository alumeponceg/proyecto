package org.iesalixar.eponceg.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.iesalixar.eponceg.model.Disease;
import org.iesalixar.eponceg.model.MedicalAppointment;
import org.iesalixar.eponceg.model.MedicalFile;
import org.iesalixar.eponceg.model.Role;
import org.iesalixar.eponceg.model.State;
import org.iesalixar.eponceg.repository.RoleRepository;
import org.iesalixar.eponceg.repository.StateRepository;
import org.iesalixar.eponceg.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	StateRepository state;

	@Autowired
	RoleRepository role;

	@Autowired
	private DiseaseService diseases;

	@Autowired
	private MedicalFileService medicalFile;

	@Autowired
	private MedicalAppointmentService medicalAppointment;
	
	final static Logger logger = LoggerFactory.getLogger(UserService.class);

	
	//Método de Login. con Spring security
	@SuppressWarnings("unchecked")
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		org.iesalixar.eponceg.model.User appUser = userRepository.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException("No existe usuario"));

		@SuppressWarnings("rawtypes")
		List grantList = new ArrayList();
		for (Role authority : appUser.getRole()) {
			GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(authority.getName());
			grantList.add(grantedAuthority);
		}

		UserDetails user = (UserDetails) new User(appUser.getEmail(), appUser.getPassword(), grantList);
		return user;
	}

	public org.iesalixar.eponceg.model.User createUser(org.iesalixar.eponceg.model.User user) {
		return this.userRepository.save(user);
	}

	public org.iesalixar.eponceg.model.User search(Long id) {
		return this.userRepository.findFirstById(id);
	}

	public List<org.iesalixar.eponceg.model.User> ListPatientOfACareer(org.iesalixar.eponceg.model.User user) {
		return this.userRepository.findByCareer(user);
	}

	public List<org.iesalixar.eponceg.model.User> ListPatientOfACareerByName(org.iesalixar.eponceg.model.User user) {
		return this.userRepository.findByCareerOrderByNameAsc(user);
	}

	public List<org.iesalixar.eponceg.model.User> ListPatientOfACareerByEmail(org.iesalixar.eponceg.model.User user) {
		return this.userRepository.findByCareerOrderByEmail(user);
	}

	public List<org.iesalixar.eponceg.model.User> ListPatientOfACareerByNameDesc(
			org.iesalixar.eponceg.model.User user) {
		return this.userRepository.findByCareerOrderByNameDesc(user);
	}

	public List<org.iesalixar.eponceg.model.User> ListPatientOfACareerAndName(org.iesalixar.eponceg.model.User user,
			String name) {
		return this.userRepository.findByCareerAndName(user, name);
	}

	public void deleteUser(Long id) {
		this.userRepository.deleteById(id);
	}

	public org.iesalixar.eponceg.model.User updateUser(org.iesalixar.eponceg.model.User user) {
		return this.userRepository.save(user);
	}

	public List<org.iesalixar.eponceg.model.User> listPatients() {
		
		//Recoge el rol paciente de la base de datos, que sabemos que tiene el id 1
		Role r = this.role.findFirstById(1L);

		Set<Role> roles = new HashSet<>();

		roles.add(r);

		return this.userRepository.findAllByRoleIn(roles);
	}

	public List<org.iesalixar.eponceg.model.User> listCarers() {
		//Recoge el rol cuidador de la base de datos, que sabemos que tiene el id 2
		Role r = this.role.findFirstById(2L);

		Set<Role> roles = new HashSet<>();

		roles.add(r);

		return this.userRepository.findAllByRoleIn(roles);
	}

	public List<org.iesalixar.eponceg.model.User> listPatientActive() {
		//Recoge el estado activo de la base de datos, que sabemos que tiene el id 1
		State s = this.state.findFirstById(1L);
		//Recoge el rol paciente de la base de datos, que sabemos que tiene el id 1
		Role r = this.role.findFirstById(1L);

		Set<Role> roles = new HashSet<>();

		roles.add(r);

		return this.userRepository.findAllByRoleInAndState(roles, s);
	}

	public List<org.iesalixar.eponceg.model.User> listPatientInactive() {
		//Recoge el estado inactivo de la base de datos, que sabemos que tiene el id 2
		State s = this.state.findFirstById(2L);
		//Recoge el rol paciente de la base de datos, que sabemos que tiene el id 1
		Role r = this.role.findFirstById(1L);

		Set<Role> roles = new HashSet<>();

		roles.add(r);

		return this.userRepository.findAllByRoleInAndState(roles, s);
	}

	public List<org.iesalixar.eponceg.model.User> listCarerInactive() {
		//Recoge el estado inactivo de la base de datos, que sabemos que tiene el id 2
		State s = this.state.findFirstById(2L);
		//Recoge el rol cuidador de la base de datos, que sabemos que tiene el id 2
		Role r = this.role.findFirstById(2L);

		Set<Role> roles = new HashSet<>();

		roles.add(r);

		return this.userRepository.findAllByRoleInAndState(roles, s);
	}

	public List<org.iesalixar.eponceg.model.User> listCarerActive() {
		//Recoge el estado activo de la base de datos, que sabemos que tiene el id 1
		State s = this.state.findFirstById(1L);
		//Recoge el rol cuidador de la base de datos, que sabemos que tiene el id 2
		Role r = this.role.findFirstById(2L);

		Set<Role> roles = new HashSet<>();

		roles.add(r);

		return this.userRepository.findAllByRoleInAndState(roles, s);
	}

	public void active(org.iesalixar.eponceg.model.User user) {
		//Recoge el estado activo de la base de datos, que sabemos que tiene el id 1
		State s = this.state.findFirstById(1L);

		user.setState(s);

		this.updateUser(user);
	}

	public void deactive(org.iesalixar.eponceg.model.User user) {
		//Recoge el estado inactivo de la base de datos, que sabemos que tiene el id 2
		State s = this.state.findFirstById(2L);

		user.setState(s);

		this.updateUser(user);
	}

	
	//Elimina un paciente y con ello desvincula de este todas las enfermedades, borra los archivos médicos y todas las citas médicas del mismo.
	public void deletePatient(String id) {
		org.iesalixar.eponceg.model.User u = this.search(Long.parseLong(id));
		Set<org.iesalixar.eponceg.model.User> users = new HashSet<>();
		users.add(u);
		List<Disease> diseases = this.diseases.readDiseases(users);

		for (Disease disease : diseases) {
			this.diseases.unlinkDisease(u, disease);
		}
		
		logger.warn("Se han desvinculado todas las enfermedades asociadas el usuario " + u.getId());
		
		List<MedicalFile> files = this.medicalFile.ListForAnUser(u);

		for (MedicalFile file : files) {
			this.medicalFile.deleteFile(file.getId());
		}
		
		logger.warn("Se han borrado todos los archivos del usuario" + u.getId());

		List<MedicalAppointment> medAps = this.medicalAppointment.ListForAnUser(u);

		for (MedicalAppointment medAp : medAps) {
			this.medicalAppointment.delete(medAp.getId());
		}
		
		logger.warn("Se han borrado todas las citas médicas del usuario " + u.getId());

		this.deleteUser(Long.parseLong(id));
	}

	//Elimina un cuidador y todos sus pacientes a cargo
	public void deleteCarer(Long id) {
		org.iesalixar.eponceg.model.User u = this.search(id);
		List<org.iesalixar.eponceg.model.User> patients = this.ListPatientOfACareer(u);

		for (org.iesalixar.eponceg.model.User patient : patients) {
			this.deletePatient(patient.getId() + "");
		}
		
		logger.warn("Se han borrado todos los pacientes a cargo del cuidador " + u.getId() + " antes de eliminar el cuidador");

		this.deleteUser(id);
	}

	
	//Devuelve el usuario conectado en ese momento.
	public org.iesalixar.eponceg.model.User userLogged() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails userDetails = null;
		if (principal instanceof UserDetails) {
			userDetails = (UserDetails) principal;
		}
		String email = userDetails.getUsername();
		Optional<org.iesalixar.eponceg.model.User> u = this.userRepository.findByEmail(email);
		return u.get();
	}

	public org.iesalixar.eponceg.model.User save(org.iesalixar.eponceg.model.User u) {

		return this.userRepository.save(u);

	}
}
