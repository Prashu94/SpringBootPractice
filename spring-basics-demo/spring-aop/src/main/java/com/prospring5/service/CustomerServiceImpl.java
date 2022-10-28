package com.prospring5.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prospring5.dto.CustomerDTO;
import com.prospring5.repository.CustomerRepository;

@Service("customerService")
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public String createCustomer(CustomerDTO customerDTO) {
		customerRepository.createCustomer(customerDTO);
		return "Customer with "+ customerDTO.getPhoneNo() + " added successfully";
	}

	@Override
	public List<CustomerDTO> fetchCustomer() {
		return customerRepository.fetchCustomers();		
	}

	@Override
	public String updateCustomer(long phoneNumber, CustomerDTO customerDTO) {
		return customerRepository.updateCustomer(phoneNumber, customerDTO);
	}

	@Override
	public String deleteCustomer(long phoneNumber) {
		return customerRepository.deleteCustomer(phoneNumber);
	}

}
