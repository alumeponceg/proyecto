package org.iesalixar.eponceg.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@IdClass(RelationMeasurementRoutine.class)
public class Measurement implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(optional = false, cascade = CascadeType.ALL )
	@JoinColumn(name="routine", insertable=false, updatable=false )
	@Id
	private Routine routine;
	
	
	@Column (nullable=false)
	private String unit;
	
	@Column (nullable=false, columnDefinition = "datetime default now()")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern ="dd/MM/yyyy")
	private Date date;
	
	@Column (nullable=false)
	private Float value;
	
	
	
	/*Constructor*/
	
	public Measurement() {
		super();
	}


	public Measurement(String unit, Date date, Float value) {
		super();
		this.unit = unit;
		this.date = date;
		this.value = value;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getUnit() {
		return unit;
	}


	public void setUnit(String unit) {
		this.unit = unit;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public Float getValue() {
		return value;
	}


	public void setValue(Float value) {
		this.value = value;
	}


	public Routine getRoutine() {
		return routine;
	}


	public void setRoutine(Routine routine) {
		this.routine = routine;
	}
	
	
	
	
	
	
	
}
