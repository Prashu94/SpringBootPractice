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
	/* CustomerService needs to contact CustomerRepository, 
	 * hence injecting the customerRepository dependency
	 */
	@Autowired
	private CustomerRepository customerRepository;
	
	//makes a call to repository method for adding the customer
	public String createCustomer(CustomerDTO customerDTO)
	{
		customerRepository.createCustomer(customerDTO);
		
		return "Customer with "+customerDTO.getPhoneNo()+" added successfully";
	}
	
	//makes a call to repository method for returning a list of customers
	public List<CustomerDTO> fetchCustomer()
	{
		List<CustomerDTO> customers = customerRepository.fetchCustomer();
		//code that iterates through customers and set the password to *
		return customers.stream().map(c->{c.setPassword("*");return c;}).collect(Collectors.toList());
	}
	 
}
