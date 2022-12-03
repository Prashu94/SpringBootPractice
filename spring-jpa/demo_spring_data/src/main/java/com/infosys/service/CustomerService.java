package com.infosys.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.infosys.dto.CustomerDTO;
import com.infosys.entity.Customer;
import com.infosys.exception.InfyBankException;

public interface CustomerService {
	public void addCustomer(CustomerDTO customer) throws InfyBankException;
	public List<CustomerDTO> findAll() throws InfyBankException;
	public CustomerDTO findById(Integer customerId) throws InfyBankException;
	public void updateCustomer(Integer customerId, String emailId) throws InfyBankException;
	public void deleteCustomer(Integer customerId) throws InfyBankException;
	
	// Service methods for Query creation using method name
	public CustomerDTO findByEmailId(String emailId) throws InfyBankException;
	public CustomerDTO findByEmailIdAndName(String emailId, String name) throws InfyBankException;
	public List<CustomerDTO> findByEmailIdOrName(String emailId, String name) throws InfyBankException;
	public List<CustomerDTO> findByDateOfBirthBetween(LocalDate fromDate, LocalDate toDate) throws InfyBankException;
	public List<CustomerDTO> findByDateOfBirthLessThan(LocalDate dateOfBirth) throws InfyBankException;
	public List<CustomerDTO> findByDateOfBirthGreaterThan(LocalDate dateOfBirth) throws InfyBankException;
	public List<CustomerDTO> findByDateOfBirthAfter(LocalDate dateOfBirth) throws InfyBankException;
	public List<CustomerDTO> findByDateOfBirthBefore(LocalDate dateOfBirth) throws InfyBankException;
	public List<CustomerDTO> findByEmailIdNull() throws InfyBankException;
	public List<CustomerDTO> findByNameLike(String pattern) throws InfyBankException;
	public List<CustomerDTO> findByNameOrderByDateOfBirth(String name) throws InfyBankException;
	public List<CustomerDTO> findByNameOrderByDateOfBirthDesc(String name) throws InfyBankException;
	
	// Service Methods for Query creation using @Query annotations
	String findByNameByEmailId(String emailId) throws InfyBankException;
	void updateCustomerEmailId(String newEmailId, Integer customerId) throws InfyBankException;
	void deleteCustomerByEmailId(String emailId) throws InfyBankException;
	
	// Service methods for @NamedQuery creation.
	String findNameByEmailId(String emailId);
	
}
