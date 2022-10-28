package com.prospring5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.AbstractApplicationContext;

import com.prospring5.dto.CustomerDTO;
import com.prospring5.service.CustomerServiceImpl;

@SpringBootApplication
public class Demo9Application {

	public static void main(String[] args) {
		CustomerServiceImpl customerService = null;
		
		AbstractApplicationContext context = (AbstractApplicationContext)SpringApplication.run(Demo9Application.class, args);
		customerService = (CustomerServiceImpl) context.getBean("customerService");
		customerService.fetchCustomer();
		
		CustomerDTO customerDTO = new CustomerDTO(9951212223l, "Prashant", "prashant@infy.com", "Mumbai");
		System.out.println(customerService.createCustomer(customerDTO));
		
		customerService.deleteCustomer(9951212224l);
		context.close();
	}

}
