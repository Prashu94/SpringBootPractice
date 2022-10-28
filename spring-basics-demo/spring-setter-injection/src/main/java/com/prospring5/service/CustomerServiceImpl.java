package com.prospring5.service;

import com.prospring5.repository.CustomerRepository;

public class CustomerServiceImpl implements CustomerService {
	
	private int count;
	private CustomerRepository customerRepository;
	
	
	
	public void setCount(int count) {
		this.count = count;
	}

	public void setCustomerRepository(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	public String createCustomer() {
		return customerRepository.createCustomer();
	}

	public String fetchCustomer() {
		return customerRepository.fetchCustomer(count);
	}

}
