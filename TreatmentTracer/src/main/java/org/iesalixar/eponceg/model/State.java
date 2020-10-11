package org.iesalixar.eponceg.model;


import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="STATE")
public class State {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column (nullable=false)
	private String name;
	
	@OneToMany(mappedBy="state",  targetEntity = User.class)  
	private Set<User> users;
	
	@OneToMany(mappedBy="treatmentState",fetch=FetchType.LAZY, cascade = CascadeType.ALL,  targetEntity = Treatment.class)  
	private Set<Treatment> treatmentsState;
	
	@OneToMany(mappedBy="routineState",fetch=FetchType.LAZY, cascade = CascadeType.ALL,  targetEntity = Routine.class)  
	private Set<Routine> routineState;
	
	/*Constructor*/
	
	public State() {
		super();
	}
	
	public State (String name) {
		this.name=name;
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
	
	

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public Set<Treatment> getTreatmentsState() {
		return treatmentsState;
	}

	public void setTreatmentsState(Set<Treatment> treatmentsState) {
		this.treatmentsState = treatmentsState;
	}

	public Set<Routine> getRoutineState() {
		return routineState;
	}

	public void setRoutineState(Set<Routine> routineState) {
		this.routineState = routineState;
	}


	
	
	
	

}
