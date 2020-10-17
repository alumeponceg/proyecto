package org.iesalixar.eponceg.service;

import org.iesalixar.eponceg.model.State;
import org.iesalixar.eponceg.repository.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StateService {

	@Autowired
	private StateRepository state;
	
	public State findFirstById(Long id) {
		return this.state.findFirstById(id);
	}
}
