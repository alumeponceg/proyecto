package org.iesalixar.eponceg.service;

import org.iesalixar.eponceg.repository.RoutineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoutineService {

	@Autowired
	private RoutineRepository routines;
}
