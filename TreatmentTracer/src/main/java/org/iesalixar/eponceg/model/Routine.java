package org.iesalixar.eponceg.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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
	
	@Column (nullable=false, columnDefinition = "datetime default now()")
	private Date activationDate;
	
	@ManyToOne
	@JoinColumn(name="routineState", nullable=false, columnDefinition = "integer default 1")
	private State routineState;
	
	@ManyToOne
	@JoinColumn(name="ownerUser", nullable=false)
	private User ownerUser;
	
	@ManyToOne
	@JoinColumn(name="disease", nullable=false)
	private Disease disease;
	
	@OneToMany(mappedBy = "routine", cascade = CascadeType.ALL)
	private Set<Measurement> measurements;
	
	
	/*Constructor*/
	
	public Routine() {
		super();
	}


	public Routine(String name, Integer posology, Integer duration, Date activationDate) {
		super();
		this.name = name;
		this.posology = posology;
		this.duration = duration;
		this.activationDate = activationDate;
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
