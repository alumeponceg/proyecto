package org.iesalixar.eponceg.quartz;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigureJob {
	
		//TRATAMIENTOS
	
	 @Bean
	    public JobDetail jobADetails() {
	        return JobBuilder.newJob(TreatmentUpdateJob.class).withIdentity("treatmentUpdate")
	                .storeDurably().build();
	    }

	    @Bean
	    public Trigger jobATrigger(JobDetail jobADetails) {

	        return TriggerBuilder.newTrigger().forJob(jobADetails)
	        		
	                .withIdentity("treatmentUpdateTrigger")
	                .withSchedule(CronScheduleBuilder.cronSchedule("0 0 14 * * ?"))
	                .build();
	    }
	    
	    //RUTINAS
	    
	    @Bean
	    public JobDetail jobADetailsRoutine() {
	        return JobBuilder.newJob(RoutineUpdateJob.class).withIdentity("routineUpdate")
	                .storeDurably().build();
	    }

	    @Bean
	    public Trigger jobATriggerRoutine(JobDetail jobADetails) {

	        return TriggerBuilder.newTrigger().forJob(jobADetails)
	        		
	                .withIdentity("routineUpdateTrigger")
	                .withSchedule(CronScheduleBuilder.cronSchedule("0 0 14 * * ?"))
	                .build();
	    }
}
