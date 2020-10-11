package org.iesalixar.eponceg.model;

import java.sql.Blob;
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
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Disease {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column (unique=true, columnDefinition = "varchar(45)")
	private String name;
	@Column (columnDefinition = "longtext")
	private String causes;
	@Column (columnDefinition = "longtext")
	private String description;
	@Column (columnDefinition = "longtext")
	private String symptom;
	
	@Lob
	private Blob image;

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
	
	/*Constructor*/
	
	public Disease() {
		super();
	}



	public Disease(String name, String causes, String description, String symptom, Blob image) {
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



	public Blob getImage() {
		return image;
	}



	public void setImage(Blob image) {
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



	@Override
	public String toString() {
		return "Disease [id=" + id + ", name=" + name + ", causes=" + causes + ", description=" + description
				+ ", symptom=" + symptom + ", image=" + image + ", treatments=" + treatments + ", routines=" + routines
				+ ", users=" + users + "]";
	}
	
	
	

}
