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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name="USER")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column (nullable=false)
	private String name;
	
	@Column (nullable=false)
	private String surname;
	
	@Column (columnDefinition = "varchar(45)", nullable=false, unique=true)
	@Pattern(regexp = "^([a-zA-Z0-9_\\.\\-])+\\@(([a-zA-Z0-9\\-])+\\.)+([a-zA-Z0-9]{2,4})+$")
	private String email;
	
	@Column (nullable=false)
	@Pattern(regexp = "^(?=\\w*\\d)(?=\\w*[A-Z])(?=\\w*[a-z])\\S{8,16}$")
	private String password;
	
	@ManyToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="state")
	private State state;
	
	@ManyToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="career", nullable=true)
	private User career;
	
	@OneToMany(mappedBy="career",fetch=FetchType.LAZY, cascade = CascadeType.ALL,  targetEntity = User.class)  
	private Set<User> users;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "roleusers", joinColumns = @JoinColumn(name = "usuarioId"), inverseJoinColumns = @JoinColumn(name = "roleId"))
	private Set<Role> role;
	
	@Column (name="dateofbirth",nullable=false)
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern ="dd/MM/yyyy")
	@Past
	private Date dateOfBirth;
	
	@Column (name="dischargedate", columnDefinition = "datetime default now()")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern ="dd/MM/yyyy")
	private Date dischargeDate= new Date();
	
	@OneToMany(mappedBy="userId",fetch=FetchType.LAZY,cascade = CascadeType.ALL, targetEntity = MedicalAppointment.class)  
	private Set<MedicalAppointment> medicalAppointments;
	
	@OneToMany(mappedBy="ownerUser",fetch=FetchType.LAZY,cascade = CascadeType.ALL, targetEntity = Treatment.class)  
	private Set<Treatment> userTreatments;
	
	@OneToMany(mappedBy="ownerUser",fetch=FetchType.LAZY,cascade = CascadeType.ALL, targetEntity = Routine.class)  
	private Set<Routine> userRoutines;
	

	@OneToMany(mappedBy="userId",fetch=FetchType.LAZY,cascade = CascadeType.ALL, targetEntity = MedicalFile.class)  
	private Set<MedicalFile> files;
	
	@ManyToMany( mappedBy = "users")
	private Set<Disease> diseases;
	
	/*Constructor*/
	
	public User() {
		super();
	}
	
	public User(String name, String surname, String email, String password, Date dateOfBirth, Date dischargeDate) {
		super();
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.password = password;
		this.dateOfBirth = dateOfBirth;
		this.dischargeDate = dischargeDate;
	}
	
	public User(String name, String surname, String email, String password, Date dateOfBirth) {
		super();
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.password = password;
		this.dateOfBirth = dateOfBirth;	
	}

	public User(String name, String surname, String email, String password, State state, User career, Set<Role> role,
			Date dateOfBirth, Date dischargeDate) {
		super();
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.password = password;
		this.state = state;
		this.career = career;
		this.role = role;
		this.dateOfBirth = dateOfBirth;
		this.dischargeDate = dischargeDate;
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

	public String getSurname() {
		return surname;
	}
	
	public User getCareer() {
		return career;
	}

	public void setCareer(User career) {
		this.career = career;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Date getDischargeDate() {
		return dischargeDate;
	}

	public void setDischargeDate(Date dischargeDate) {
		this.dischargeDate = dischargeDate;
	}

	public Set<MedicalAppointment> getMedicalAppointments() {
		return medicalAppointments;
	}

	public void setMedicalAppointments(Set<MedicalAppointment> medicalAppointments) {
		this.medicalAppointments = medicalAppointments;
	}

	public Set<Treatment> getUserTreatments() {
		return userTreatments;
	}

	public void setUserTreatments(Set<Treatment> treatments) {
		this.userTreatments = treatments;
	}

	public Set<MedicalFile> getFiles() {
		return files;
	}

	public void setFiles(Set<MedicalFile> files) {
		this.files = files;
	}

	public Set<Routine> getUserRoutines() {
		return userRoutines;
	}

	public void setUserRoutines(Set<Routine> userRoutines) {
		this.userRoutines = userRoutines;
	}

	public Set<Disease> getDiseases() {
		return diseases;
	}

	public void setDiseases(Set<Disease> diseases) {
		this.diseases = diseases;
	}

	public Set<Role> getRole() {
		return role;
	}

	public void setRole(Set<Role> role) {
		this.role = role;
	}

	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
