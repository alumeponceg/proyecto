package org.iesalixar.eponceg.model;

import java.io.Serializable;

public class RelationMeasurementRoutine implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Routine routine;
    private Long id;
	
	public Routine getRoutine() {
		return routine;
	}
	public void setRoutine(Routine routine) {
		this.routine = routine;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
    
    

}
