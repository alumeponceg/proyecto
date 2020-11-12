package org.iesalixar.eponceg.service;

import org.iesalixar.eponceg.model.Role;
import org.iesalixar.eponceg.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

	@Autowired
	private RoleRepository role;
	
	public Role findFirstById(Long id) {
		return this.role.findFirstById(id);
	}
}
