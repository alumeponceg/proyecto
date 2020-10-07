package org.iesalixar.eponceg.model;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

@Entity
public class MedicalFile {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column (nullable=false)
	private String title;
	
	@Column
	private String description;
	
	@Column (nullable=false, columnDefinition = "datetime default now()")
	private Date uploadDate;
	
	@Column(nullable=false, length = 100000)
	private byte[] file;
	

	@ManyToOne
	@JoinColumn(name="user_id", nullable=false)
	private User user_id;
	
	
	
	/*Constructor*/
	
	public MedicalFile() {
		super();
	}



	public MedicalFile(String title, String description, Date uploadDate, byte[] file) {
		super();
		this.title = title;
		this.description = description;
		this.uploadDate = uploadDate;
		this.file = file;
	}
	
	public MedicalFile(String title, String description, byte[] file) {
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



	public byte[] getFile() {
		return file;
	}



	public void setFile(byte[] file) {
		this.file = file;
	}



	public User getUser_id() {
		return user_id;
	}



	public void setUser_id(User user_id) {
		this.user_id = user_id;
	}
	
	
	
}
