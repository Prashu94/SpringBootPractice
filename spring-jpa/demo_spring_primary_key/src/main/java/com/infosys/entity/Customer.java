package com.infosys.entity;

import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.TableGenerator;

@Entity
public class Customer {
	
	
	@Id
	// IDENTITY
	//@GeneratedValue(strategy=GenerationType.IDENTITY)
	// DEFAULT table - hibernate_sequences
	//@GeneratedValue(strategy = GenerationType.TABLE)
	// Custom Table
	@TableGenerator(
			name = "pk_gen",
			table = "id_gen",
			pkColumnName = "gen_key",
			valueColumnName = "gen_value",
			pkColumnValue = "cust_id",
			allocationSize = 1
			)
	@GeneratedValue(generator = "pk_gen", strategy = GenerationType.TABLE)
	private Integer customerId;
	private String emailId;
	private String name;
	private LocalDate dateOfBirth;
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	@Override
	public int hashCode() {
		return Objects.hash(customerId);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		return Objects.equals(customerId, other.customerId);
	}

}
