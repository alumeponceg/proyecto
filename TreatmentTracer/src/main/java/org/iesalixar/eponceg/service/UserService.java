package org.iesalixar.eponceg.service;

import org.iesalixar.eponceg.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired
	private UserRepository user;
}
