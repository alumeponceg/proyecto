package org.iesalixar.eponceg.service;

import org.iesalixar.eponceg.repository.MedicalFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicalFileService {

	@Autowired
	private MedicalFileRepository medicalFile;
}
