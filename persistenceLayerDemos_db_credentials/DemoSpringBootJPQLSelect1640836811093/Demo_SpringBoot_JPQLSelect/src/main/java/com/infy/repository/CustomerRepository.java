package com.infy.repository;

import java.util.List;

import com.infy.dto.CustomerDTO;


public interface CustomerRepository {

	public List<CustomerDTO> getCustomerdetails();
	public List<Object[]> getCustomerNameAndDob();
	public List<String> getCustomerName();
	
}
