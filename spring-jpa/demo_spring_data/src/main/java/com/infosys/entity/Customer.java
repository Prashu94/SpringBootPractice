package com.infosys.entity;

import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;







@Entity
public class Customer {
	@Id
	private Integer customerId;
	private String city;
	private String emailId;
	private String name;
	private LocalDate dateOfBirth;
	
	public Customer() {
		super();
	}

	public Customer(Integer customerId, String emailId, String name, String city, LocalDate dateOfBirth) {
		super();
		this.customerId = customerId;
		this.emailId = emailId;
		this.name = name;
		this.city = city;
		this.dateOfBirth = dateOfBirth;
	}

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
	

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
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

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", city=" + city + ", emailId=" + emailId + ", name=" + name
				+ ", dateOfBirth=" + dateOfBirth + "]";
	}

	
	
	
	
}
