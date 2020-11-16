package org.iesalixar.eponceg.quartz;

import org.iesalixar.eponceg.model.Routine;
import org.iesalixar.eponceg.model.State;
import org.iesalixar.eponceg.service.RoutineService;
import org.iesalixar.eponceg.service.StateService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

public class RoutineUpdateJob implements Job {

	@Autowired
	private RoutineService routines;
	@Autowired
	private StateService state;
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {

		State s = this.state.findFirstById(2L);
		for(Routine r : this.routines.findAllByExpirationDateLessThan()) {
			System.out.println(r.getName());
			r.setRoutineState(s);
			
			this.routines.updateRoutine(r);
			System.out.println(r.getRoutineState().getName());
		}
	}
}
