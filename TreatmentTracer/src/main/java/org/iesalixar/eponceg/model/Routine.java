package org.iesalixar.eponceg.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Routine {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column (nullable=false)
	private String name;
	
	@Column (nullable=false)
	private Integer posology;
	
	@Column (nullable=false)
	private Integer duration;
	
	@Column (name="activationdate", nullable=false, columnDefinition = "datetime default now()")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern ="dd/MM/yyyy")
	private Date activationDate;
	
	@ManyToOne
	@JoinColumn(name="routinestate", nullable=false, columnDefinition = "integer default 1")
	private State routineState;
	
	@ManyToOne
	@JoinColumn(name="owneruser", nullable=false)
	private User ownerUser;
	
	@ManyToOne
	@JoinColumn(name="disease", nullable=false)
	private Disease disease;
	
	@OneToMany(mappedBy = "routine", fetch=FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Measurement> measurements;
	
	
	/*Constructor*/
	
	public Routine() {
		super();
	}


	public Routine(String name, Integer posology, Integer duration, Disease d) {
		super();
		this.name = name;
		this.posology = posology;
		this.duration = duration;
		this.disease= d;
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


	public State getRoutineState() {
		return routineState;
	}


	public void setRoutineState(State routineState) {
		this.routineState = routineState;
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


	public Set<Measurement> getMeasurements() {
		return measurements;
	}


	public void setMeasurements(Set<Measurement> measurements) {
		this.measurements = measurements;
	}
	
	
	
	
}
