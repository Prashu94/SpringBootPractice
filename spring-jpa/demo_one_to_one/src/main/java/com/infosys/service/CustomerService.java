package com.infosys.service;

import com.infosys.dto.AddressDTO;
import com.infosys.dto.CustomerDTO;
import com.infosys.exception.InfyBankException;

public interface CustomerService {
	public CustomerDTO getCustomer(Integer customerId) throws InfyBankException;
	public Integer addCustomer(CustomerDTO customerDTO) throws InfyBankException;
	public void updateAddress(Integer customerId, AddressDTO addressDTO) throws InfyBankException;
	public void deleteCustomer(Integer customerId) throws InfyBankException;
	public void deleteCustomerOnly(Integer customerId) throws InfyBankException;
}
