package com.infosys.service;

import java.util.List;

import com.infosys.dto.CustomerDTO;
import com.infosys.entity.Customer;

public interface CustomerService {
	
	public void insertCustomer(CustomerDTO customer);
	public void removeCustomer(Long phoneNo);
	public CustomerDTO getCustomer(Long phoneNo);
	public String updateCustomer(Long phoneNo, Integer newPlanId);
	public List<Customer> findAll();
}
