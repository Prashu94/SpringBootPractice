package com.prospring5.service;

import java.util.List;

import com.prospring5.dto.CustomerDTO;

public interface CustomerService {
	public String createCustomer(CustomerDTO customerDTO);
	
	public List<CustomerDTO> fetchCustomer();
}
