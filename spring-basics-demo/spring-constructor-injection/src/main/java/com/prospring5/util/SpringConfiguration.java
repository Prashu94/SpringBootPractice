package com.prospring5.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.prospring5.repository.CustomerRepository;
import com.prospring5.service.CustomerServiceImpl;

@Configuration
public class SpringConfiguration {
	
	@Bean // constructor injection
	public CustomerServiceImpl customerService() {
		return new CustomerServiceImpl(customerRepository(), 5);
	}
	
	@Bean // constructor injection
	public CustomerRepository customerRepository() {
		return new CustomerRepository();
	}
}
