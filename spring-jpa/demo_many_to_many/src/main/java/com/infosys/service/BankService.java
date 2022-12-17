package com.infosys.service;

import java.util.List;

import com.infosys.dto.CustomerDTO;
import com.infosys.exception.InfyBankException;

public interface BankService {
	public Integer addCustomerAndService(CustomerDTO customerDTO) throws InfyBankException;
	public void addExistingServiceToExistingCustomer(Integer customerId, List<Integer> serviceIds) throws InfyBankException;
	public void deallocateServiceForExistingCustomer(Integer customerId,List<Integer> serviceIds) throws InfyBankException;	
	public void deleteCustomer(Integer customerId) throws InfyBankException;
}
