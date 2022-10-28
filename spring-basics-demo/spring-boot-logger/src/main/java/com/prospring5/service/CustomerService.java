package com.prospring5.service;

import com.prospring5.dto.CustomerDTO;

public interface CustomerService {
	public String createCustomer(CustomerDTO customer);
	
	public String fetchCustomer();
	
	public void deleteCustomer(long phoneNumber) throws Exception;
}
