package org.iesalixar.eponceg.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
public class Treatment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column (nullable=false)
	private String name;
	
	@Column (nullable=false)
	private Integer posology;
	
	@Column (nullable=false)
	private Integer duration;
	
	@Column (name="activationdate" ,columnDefinition = "datetime default now()")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern ="dd/MM/yyyy")
	private Date activationDate;
	
	@Column (name="expirationdate" ,columnDefinition = "datetime default now()")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern ="dd/MM/yyyy")
	private Date expirationDate;
	
	@ManyToOne
	@JoinColumn(name="treatmentstate", nullable=false, columnDefinition = "integer default 1")
	private State treatmentState;
	
	@ManyToOne
	@JoinColumn(name="owneruser", nullable=false)
	private User ownerUser;
	
	@ManyToOne
	@JoinColumn(name="disease", nullable=false)
	private Disease disease;
	
	/*Constructor*/
	
	public Treatment() {
		super();
	}

	public Treatment(String name, Integer posology, Integer duration, Disease disease) {
		super();
		this.name = name;
		this.posology = posology;
		this.duration = duration;
		this.disease= disease;

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPosology() {
		return posology;
	}

	public void setPosology(Integer posology) {
		this.posology = posology;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public Date getActivationDate() {
		return activationDate;
	}

	public void setActivationDate(Date activationDate) {
		this.activationDate = activationDate;
	}

	public State getTreatmentState() {
		return treatmentState;
	}

	public void setTreatmentState(State treatmentState) {
		this.treatmentState = treatmentState;
	}

	public User getOwnerUser() {
		return ownerUser;
	}

	public void setOwnerUser(User ownerUser) {
		this.ownerUser = ownerUser;
	}

	public Disease getDisease() {
		return disease;
	}

	public void setDisease(Disease disease) {
		this.disease = disease;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	
	
	
	
	
	
	
	

}
