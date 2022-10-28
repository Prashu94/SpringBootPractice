package com.prospring5.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.prospring5.repository.CustomerRepository;
import com.prospring5.service.CustomerServiceImpl;

@Configuration
public class SpringConfiguration {
	
	@Bean 
	public CustomerRepository customerRepository() {
		return new CustomerRepository();
	}
	
	@Bean // setter injection
	public CustomerServiceImpl customerService() {
		CustomerServiceImpl customerServiceImpl = new CustomerServiceImpl();
		customerServiceImpl.setCount(5);
		customerServiceImpl.setCustomerRepository(customerRepository());
		return customerServiceImpl;
	}
}
