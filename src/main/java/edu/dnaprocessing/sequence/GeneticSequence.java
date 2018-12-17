package edu.dnaprocessing.sequence;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;

import org.hibernate.annotations.GenericGenerator;

/**
 * A general entity class for genetic sequence representation
 */
@Entity
@Inheritance
public abstract class GeneticSequence {
	@Id @GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	private String id;
	private String sequence;
	private String description;
	private boolean valid;
	
	public GeneticSequence() {
		this.valid = true;
	}

	public GeneticSequence(String id, String sequence, String description) {
		this.id = id;
		this.sequence = sequence;
		this.description = description;
		this.valid = true;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSequence() {
		return sequence;
	}
	public void setSequence(String sequence) {
		this.sequence = sequence;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isValid() {
		return valid;
	}
	public void setValid(boolean valid) {
		this.valid = valid;
	}
}
