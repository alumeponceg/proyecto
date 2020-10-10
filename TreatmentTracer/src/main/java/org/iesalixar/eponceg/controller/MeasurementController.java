package org.iesalixar.eponceg.controller;

import org.iesalixar.eponceg.service.MeasurementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MeasurementController {

	@Autowired
	private MeasurementService measurements;
}
