package org.iesalixar.eponceg.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.iesalixar.eponceg.model.Disease;
import org.iesalixar.eponceg.model.MedicalAppointment;
import org.iesalixar.eponceg.model.MedicalFile;
import org.iesalixar.eponceg.model.Role;
import org.iesalixar.eponceg.model.State;
import org.iesalixar.eponceg.repository.RoleRepository;
import org.iesalixar.eponceg.repository.StateRepository;
import org.iesalixar.eponceg.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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
		
	    @SuppressWarnings("unchecked")
		@Override
	     public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
			
	     org.iesalixar.eponceg.model.User appUser = 
	                 userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("No existe usuario"));
			
	    @SuppressWarnings("rawtypes")
		List grantList = new ArrayList();
	    for (Role authority: appUser.getRole()) {
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
	
	public List<org.iesalixar.eponceg.model.User> ListPatientOfACareer(org.iesalixar.eponceg.model.User user){
		return this.userRepository.findByCareer(user);
	}
	public List<org.iesalixar.eponceg.model.User> ListPatientOfACareerByName(org.iesalixar.eponceg.model.User user){
		return this.userRepository.findByCareerOrderByNameAsc(user);
	}
	public List<org.iesalixar.eponceg.model.User> ListPatientOfACareerByEmail(org.iesalixar.eponceg.model.User user){
		return this.userRepository.findByCareerOrderByEmail(user);
	}
	public List<org.iesalixar.eponceg.model.User> ListPatientOfACareerByNameDesc(org.iesalixar.eponceg.model.User user){
		return this.userRepository.findByCareerOrderByNameDesc(user);
	}
	
	public List<org.iesalixar.eponceg.model.User> ListPatientOfACareerAndName(org.iesalixar.eponceg.model.User user, String name){
		return this.userRepository.findByCareerAndName(user, name);
	}
	
	public void deleteUser (Long id) {
		this.userRepository.deleteById(id);
	}
	
	public org.iesalixar.eponceg.model.User updateUser (org.iesalixar.eponceg.model.User user) {
		return this.userRepository.save(user);
	}
	
	public List<org.iesalixar.eponceg.model.User> listPatients() {
		Role r = this.role.findFirstById(1L);
		
		Set<Role> roles = new HashSet<>();
		
		roles.add(r);
		
		return this.userRepository.findAllByRoleIn(roles);
	}
	
	public List<org.iesalixar.eponceg.model.User> listCarers() {
		Role r = this.role.findFirstById(2L);
		
		Set<Role> roles = new HashSet<>();
		
		roles.add(r);
		
		return this.userRepository.findAllByRoleIn(roles);
	}
	
	public List<org.iesalixar.eponceg.model.User> listPatientActive () {
		State s = this.state.findFirstById(1L);
		Role r = this.role.findFirstById(1L);
		
		Set<Role> roles = new HashSet<>();
		
		roles.add(r);
		
		return this.userRepository.findAllByRoleInAndState(roles, s);
	}
	
	public List<org.iesalixar.eponceg.model.User> listPatientInactive () {
		State s = this.state.findFirstById(2L);
		Role r = this.role.findFirstById(1L);
		
		Set<Role> roles = new HashSet<>();
		
		roles.add(r);
		
		return this.userRepository.findAllByRoleInAndState(roles, s);
	}
	
	public List<org.iesalixar.eponceg.model.User> listCarerInactive () {
		State s = this.state.findFirstById(2L);
		Role r = this.role.findFirstById(2L);
		
		Set<Role> roles = new HashSet<>();
		
		roles.add(r);
		
		return this.userRepository.findAllByRoleInAndState(roles, s);
	}
	
	public List<org.iesalixar.eponceg.model.User> listCarerActive () {
		State s = this.state.findFirstById(1L);
		Role r = this.role.findFirstById(2L);
		
		Set<Role> roles = new HashSet<>();
		
		roles.add(r);
		
		return this.userRepository.findAllByRoleInAndState(roles, s);
	}
	
	public void active(org.iesalixar.eponceg.model.User user) {
		State s = this.state.findFirstById(1L);
		
		user.setState(s);
		
		this.updateUser(user);
	}
	
	public void deactive(org.iesalixar.eponceg.model.User user) {
		State s = this.state.findFirstById(2L);
		
		user.setState(s);
		
		this.updateUser(user);
	}
	
	public void deletePatient(String id) {
		org.iesalixar.eponceg.model.User u = this.search(Long.parseLong(id));
		Set<org.iesalixar.eponceg.model.User> users = new HashSet<>();
		users.add(u);
		List<Disease> diseases = this.diseases.readDiseases(users);
		
		for (Disease disease : diseases) {
			this.diseases.unlinkDisease(u, disease);
		}
		
		List<MedicalFile> files = this.medicalFile.ListForAnUser(u);
		
		for (MedicalFile file : files) {
			this.medicalFile.deleteFile(file.getId());
		}
		
		List<MedicalAppointment> medAps = this.medicalAppointment.ListForAnUser(u);
		
		for (MedicalAppointment medAp : medAps) {
			this.medicalAppointment.delete(medAp.getId());
		}
		
		this.deleteUser(Long.parseLong(id));
	}
	
	public void deleteCarer(Long id) {
		org.iesalixar.eponceg.model.User u = this.search(id);
		List<org.iesalixar.eponceg.model.User> patients = this.ListPatientOfACareer(u);
		
		for (org.iesalixar.eponceg.model.User patient : patients) {
			this.deletePatient(patient.getId()+"");
		}
		
		this.deleteUser(id);
	}
	
//	public String countPatient(org.iesalixar.eponceg.model.User carer) {
//		return this.countPatient(carer);
//	}
//	
}
