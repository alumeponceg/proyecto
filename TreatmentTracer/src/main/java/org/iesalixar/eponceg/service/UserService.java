package org.iesalixar.eponceg.service;

import org.iesalixar.eponceg.model.User;
import org.iesalixar.eponceg.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired
	private UserRepository users;
	
	public User checkUser(String email, String password) {
		return this.users.findByEmailAndPassword(email, password);
	}
	
	public User createUser(User user) {
		return this.users.save(user);
	}
	
	public User search(Long id) {
		return this.users.findFirstById(id);
	}
}
