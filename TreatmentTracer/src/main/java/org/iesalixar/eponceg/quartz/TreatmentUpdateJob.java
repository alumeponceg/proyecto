package org.iesalixar.eponceg.quartz;

import org.iesalixar.eponceg.model.State;
import org.iesalixar.eponceg.model.Treatment;
import org.iesalixar.eponceg.service.StateService;
import org.iesalixar.eponceg.service.TreatmentService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

public class TreatmentUpdateJob implements Job {

	@Autowired
	private TreatmentService treatments;
	@Autowired
	private StateService state;
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {

		State s = this.state.findFirstById(2L);
		for(Treatment t : this.treatments.findAllByExpirationDateLessThan()) {
			System.out.println(t.getName());
			t.setTreatmentState(s);
			
			this.treatments.updateTreatment(t);
			System.out.println(t.getTreatmentState().getName());
		}
	}

}
