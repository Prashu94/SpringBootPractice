package com.prospring5.service;

import com.prospring5.repository.CustomerRepository;

public class CustomerServiceImpl implements CustomerService{
	
	private int count;
	private CustomerRepository customerRepository;

	public CustomerServiceImpl(CustomerRepository customerRepository, int count) {
		super();
		this.customerRepository = customerRepository;
		this.count = count;
	}
	
	//@Override
	public String createCustomer() {
		return customerRepository.createCustomer();
	}

	//@Override
	public String fetchCustomer() {
		return customerRepository.fetchCustomer(count);
	}

}
