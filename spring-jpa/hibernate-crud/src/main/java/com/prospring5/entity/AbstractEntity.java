package com.prospring5.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public abstract class AbstractEntity implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(updatable=false)
	protected Long id;
	private int version;
	
	/**
	 * Returns the entity identifier. This entity is unique per entity. It is used by persistence frameworks used in a project,
	 * although it is public, it should not be used by application code.
	 * This identifier is mapped by ORM(Object Relational Mapper) to the database primary key of the Person record to 
	 * which the entity instance is mapped.
	 * @return the unique identifier
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * Sets the entity identifier. This identifier is unique per entity.
	 * @param id the unique identifier
	 */
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		return id != null ? id.hashCode() : 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractEntity other = (AbstractEntity) obj;
		return Objects.equals(id, other.id);
	}
	
	
	
}
