package org.iesalixar.eponceg.controller;

import java.util.List;

import org.iesalixar.eponceg.model.Treatment;
import org.iesalixar.eponceg.model.User;
import org.iesalixar.eponceg.service.TreatmentService;
import org.iesalixar.eponceg.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TreatmentController {

	@Autowired
	private TreatmentService treatments;
	
	@Autowired
	private UserService users;
	
	@RequestMapping(value="/treatments")
	public List<Treatment> Read(){
		User u = users.search(2L);
		return this.treatments.listTreatmentsForAnUser(u);
	}
	@RequestMapping(value="/treatmentsOrder")
	public List<Treatment> ReadOrder(){
		User u = users.search(2L);
		return this.treatments.listTreatmentsOrderByName(u);
	}
}
