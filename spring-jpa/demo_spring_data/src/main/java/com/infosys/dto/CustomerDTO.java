package com.infosys.dto;

import java.time.LocalDate;

public class CustomerDTO {
	private Integer customerId;
	private String city;
	private String emailId;
	private String name;
	private LocalDate dateOfBirth;
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
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
	public String toString() {
		return "CustomerDTO [customerId=" + customerId + ", city=" + city + ", emailId=" + emailId + ", name=" + name
				+ ", dateOfBirth=" + dateOfBirth + "]";
	}
	
	
	
}
