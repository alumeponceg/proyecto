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
@Table(name="ROLE")
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column (nullable=false)
	private String name;
	
	private String description;
	
	@OneToMany(mappedBy="role", fetch=FetchType.LAZY, cascade = CascadeType.ALL,  targetEntity = User.class)  
	private Set<User> users;
	
	/*Constructor*/
	
	public Role() {
		super();
	}

	public Role(String name, String description) {
		this.name = name;
		this.description = description;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
	
}
