package com.infy.repository;

import com.infy.dto.CustomerDTO;

public interface CustomerRepository{	
	public CustomerDTO getCustomer(Integer customerId);
	public void addCustomer(CustomerDTO customerDTO);
}
