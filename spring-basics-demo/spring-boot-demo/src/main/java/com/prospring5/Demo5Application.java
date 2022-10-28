package com.prospring5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.AbstractApplicationContext;

import com.prospring5.service.CustomerServiceImpl;

@SpringBootApplication
public class Demo5Application {

	public static void main(String[] args) {
		CustomerServiceImpl customerService = null;
		AbstractApplicationContext context =  (AbstractApplicationContext)SpringApplication.run(Demo5Application.class, args);
		customerService = (CustomerServiceImpl)context.getBean("customerService");
		System.out.println(customerService.createCustomer());
		System.out.println(customerService.fetchCustomer());
		context.close();
	}

}
