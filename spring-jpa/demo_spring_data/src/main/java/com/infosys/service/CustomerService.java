package com.infosys.service;

import java.util.List;

import com.infosys.dto.CustomerDTO;
import com.infosys.exception.InfyBankException;

public interface CustomerService {
	public void addCustomer(CustomerDTO customer) throws InfyBankException;
	public List<CustomerDTO> findAll() throws InfyBankException;
	public CustomerDTO findById(Integer customerId) throws InfyBankException;
	public void updateCustomer(Integer customerId, String emailId) throws InfyBankException;
	public void deleteCustomer(Integer customerId) throws InfyBankException;
	
	// Service methods for Query creation using method name
}
