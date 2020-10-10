package org.iesalixar.eponceg.controller;

import org.iesalixar.eponceg.service.DiseaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DiseaseController {

	@Autowired
	private DiseaseService diseases;
}
