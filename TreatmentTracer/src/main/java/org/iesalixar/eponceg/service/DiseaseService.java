package org.iesalixar.eponceg.service;

import org.iesalixar.eponceg.repository.DiseaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiseaseService {

	@Autowired
	private DiseaseRepository disease;
}
