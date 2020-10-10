package org.iesalixar.eponceg.controller;

import org.iesalixar.eponceg.service.MedicalFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MedicalFileController {

	@Autowired
	private MedicalFileService medicalFiles;
}
