package org.iesalixar.eponceg.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

@Entity
public class Disease {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column (unique=true, nullable=false, columnDefinition = "varchar(45)")
	private String name;
	@Size(min = 20)
	@Column (columnDefinition = "longtext")
	private String causes;
	@Size(min = 20)
	@Column (columnDefinition = "longtext")
	private String description;
	@Size(min = 20)
	@Column (columnDefinition = "longtext")
	private String symptom;
	@Column (columnDefinition = "longtext")
	private String image;

	@OneToMany(mappedBy="disease",fetch=FetchType.LAZY, cascade = CascadeType.ALL,  targetEntity = Treatment.class)  
	private Set<Treatment> treatments;
	
	@OneToMany(mappedBy="disease",fetch=FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = Routine.class)  
	private Set<Routine> routines;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
        name = "userdisease", 
        joinColumns = { @JoinColumn(name = "disease") }, 
        inverseJoinColumns = { @JoinColumn(name = "user") }
    )
	private Set<User> users;
	

	@ManyToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="state")
	private State state;
	
	/*Constructor*/
	
	public Disease() {
		super();
	}



	public Disease(String name, String causes, String description, String symptom, String image) {
		super();
		this.name = name;
		this.causes = causes;
		this.description = description;
		this.symptom = symptom;
		this.image = image;
	}
	
	public Disease(String name, String causes, String description, String symptom) {
		super();
		this.name = name;
		this.causes = causes;
		this.description = description;
		this.symptom = symptom;
		
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



	public String getCauses() {
		return causes;
	}



	public void setCauses(String causes) {
		this.causes = causes;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public String getSymptom() {
		return symptom;
	}



	public void setSymptom(String symptom) {
		this.symptom = symptom;
	}



	public String getImage() {
		return image;
	}



	public void setImage(String image) {
		this.image = image;
	}



	public Set<Treatment> getTreatments() {
		return treatments;
	}



	public void setTreatments(Set<Treatment> treatments) {
		this.treatments = treatments;
	}



	public Set<Routine> getRoutines() {
		return routines;
	}



	public void setRoutines(Set<Routine> routines) {
		this.routines = routines;
	}



	public Set<User> getUsers() {
		return users;
	}



	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public State getState() {
		return state;
	}



	public void setState(State state) {
		this.state = state;
	}



	@Override
	public String toString() {
		return "Disease [id=" + id + ", name=" + name + ", causes=" + causes + ", description=" + description
				+ ", symptom=" + symptom + ", image=" + image + ", treatments=" + treatments + ", routines=" + routines
				+ ", users=" + users + "]";
	}
	
	
	

}
