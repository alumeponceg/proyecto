package org.iesalixar.eponceg.service;

import java.util.ArrayList;
import java.util.List;

import org.iesalixar.eponceg.model.Role;
import org.iesalixar.eponceg.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;

@Service
public class UserService implements UserDetailsService {

	 @Autowired
	    UserRepository userRepository;
		
	    @Override
	     public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
			
	     org.iesalixar.eponceg.model.User appUser = 
	                 userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("No existe usuario"));
			
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
}
