package org.iesalixar.eponceg.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class MedicalAppointment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column (nullable=false)
	private String title;
	
	@Column (nullable=false)
	private String specialty;
	
	private String annotations;
	
	@Column (nullable=false)
	private Date appointmentDate;
	
	@ManyToOne
	@JoinColumn(name="user_id", nullable=false)
	private User user_id;
	
	
	/*Constructor*/
	
	public MedicalAppointment() {
		super();
	}


	public MedicalAppointment(String title, String specialty, String annotations, Date appointmentDate) {
		super();
		this.title = title;
		this.specialty = specialty;
		this.annotations = annotations;
		this.appointmentDate = appointmentDate;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getSpecialty() {
		return specialty;
	}


	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}


	public String getAnnotations() {
		return annotations;
	}


	public void setAnnotations(String annotations) {
		this.annotations = annotations;
	}


	public Date getAppointmentDate() {
		return appointmentDate;
	}


	public void setAppointmentDate(Date appointmentDate) {
		this.appointmentDate = appointmentDate;
	}


	public User getUser_id() {
		return user_id;
	}


	public void setUser_id(User user_id) {
		this.user_id = user_id;
	}
	
	
	
	
}
