package org.iesalixar.eponceg.controller;

import org.iesalixar.eponceg.service.TreatmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TreatmentController {

	@Autowired
	private TreatmentService treatments;
}
