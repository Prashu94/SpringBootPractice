package com.prospring5.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.prospring5.service.CustomerServiceImpl;

@Configuration
public class SpringConfiguration {
	
	@Bean(name="customerServiceImpl")
	public CustomerServiceImpl customerServiceImpl() {
		return new CustomerServiceImpl();
	}
}
