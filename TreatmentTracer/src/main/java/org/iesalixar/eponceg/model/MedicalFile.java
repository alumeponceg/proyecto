package org.iesalixar.eponceg.model;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="medicalfile")
public class MedicalFile {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column (nullable=false)
	private String title;
	
	@Column
	private String description;
	
	@Column (name="uploaddate",nullable=false, columnDefinition = "datetime default now()")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern ="dd/MM/yyyy")
	private Date uploadDate;
	
	@Column(nullable=false, length = 100000, columnDefinition = "longtext")
	private String file;
	

	@ManyToOne
	@JoinColumn(name="userid", nullable=false)
	private User userId;
	
	
	
	/*Constructor*/
	
	public MedicalFile() {
		super();
	}



	public MedicalFile(String title, String description, Date uploadDate, String file) {
		super();
		this.title = title;
		this.description = description;
		this.uploadDate = uploadDate;
		this.file = file;
	}
	
	public MedicalFile(String title, String description, String file) {
		super();
		this.title = title;
		this.description = description;
		this.file = file;
		this.uploadDate = new Date();
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



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public Date getUploadDate() {
		return uploadDate;
	}



	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}



	public String getFile() {
		return file;
	}



	public void setFile(String file) {
		this.file = file;
	}



	public User getUserId() {
		return userId;
	}



	public void setUserId(User user_id) {
		this.userId = user_id;
	}
	
	
	
}
