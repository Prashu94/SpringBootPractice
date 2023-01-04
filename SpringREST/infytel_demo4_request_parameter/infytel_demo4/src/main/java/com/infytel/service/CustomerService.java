package com.infytel.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infytel.dto.CustomerDTO;
import com.infytel.repository.CustomerRepository;
@Service
public class CustomerService 
{
	@Autowired
	private CustomerRepository customerRepository;
	
	// calls repository layer  method to create customer
	public String createCustomer(CustomerDTO customerDTO)
	{
		return customerRepository.createCustomer(customerDTO);
	}
	
	//makes a call to repository method for returning a list of customers
		public List<CustomerDTO> fetchCustomer()
		{
			List<CustomerDTO> customers = customerRepository.fetchCustomer();
			//code that iterates through customers and set the password to *
			return customers.stream().map(c->{c.setPassword("*");return c;}).collect(Collectors.toList());
		}
		
		
	
	// calls repository layer  method to delete customer
	public String deleteCustomer(long phoneNumber)
	{
		return customerRepository.deleteCustomer(phoneNumber);
	}
	
	// calls repository layer  method to update customer
	public String updateCustomer(long phoneNumber, CustomerDTO customerDTO)
	{
		return customerRepository.updateCustomer(phoneNumber, customerDTO);
	}
}
