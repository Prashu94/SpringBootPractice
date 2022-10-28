package com.prospring5.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prospring5.dto.CustomerDTO;
import com.prospring5.repository.CustomerRepository;

@Service("customerService")
public class CustomerServiceImpl implements CustomerService{

	//@Autowired // Autowiring Properties
	private CustomerRepository customerRepository;
	
	//@Autowired // Autowiring constructors
	public CustomerServiceImpl (CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}
	
	@Autowired // Autowiring setters
	public void setCustomerRepository(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}



	@Override
	public String createCustomer(CustomerDTO customerDTO) {
		customerRepository.createCustomer(customerDTO);
		return "Customer with " + customerDTO.getPhoneNo() + " added successfully";
	}

	@Override
	public List<CustomerDTO> fetchCustomer() {
		return customerRepository.fetchCustomer();
	}

}
