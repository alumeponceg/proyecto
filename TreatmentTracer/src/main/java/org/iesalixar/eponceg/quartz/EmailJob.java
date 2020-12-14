package org.iesalixar.eponceg.quartz;

import org.iesalixar.eponceg.email.EmailBody;
import org.iesalixar.eponceg.email.EmailService;
import org.iesalixar.eponceg.model.Routine;
import org.iesalixar.eponceg.model.Treatment;
import org.iesalixar.eponceg.service.RoutineService;
import org.iesalixar.eponceg.service.TreatmentService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

public class EmailJob implements Job {

	@Autowired
	private EmailService emailService;
	@Autowired
	private RoutineService routines;
	@Autowired
	private TreatmentService treatments;

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {

		for (Routine r : this.routines.findAllByExpirationDateBetween()) {
			String email = r.getOwnerUser().getEmail();
			String subject = "CADUCIDAD DE LA RUTINA " + r.getName();
			String content = "La rutina " + r.getName()
					+ " caduca hoy en las próximas 24Horas y será desactivada. Puede volver a activarla o modificar la fecha de expiración desde la aplicación";
			
			EmailBody emailBody = new EmailBody();
			emailBody.setContent(content);
			emailBody.setEmail(email);
			emailBody.setSubject(subject);
			emailService.sendEmail(emailBody);
		}
		for (Treatment t : this.treatments.findAllByExpirationDateBetween()) {
			String email = t.getOwnerUser().getEmail();
			String subject = "CADUCIDAD DEL TRATAMIENTO " + t.getName().toUpperCase();
			String content = "El tratamiento " + t.getName()
					+ " caduca hoy en las próximas 24Horas y será desactivado. Puede volver a activarlo o modificar la fecha de expiración desde la aplicación";
			EmailBody emailBody = new EmailBody();
			emailBody.setContent(content);
			emailBody.setEmail(email);
			emailBody.setSubject(subject);
			emailService.sendEmail(emailBody);
		}
		
	}

}
