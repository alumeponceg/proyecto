package org.iesalixar.eponceg.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class TreatmentUpdateJob implements Job {

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {

		System.out.println("ESTOY FUNCIONANDO SOY UNA TAREA PROGRAMADA");
		
	}

}
