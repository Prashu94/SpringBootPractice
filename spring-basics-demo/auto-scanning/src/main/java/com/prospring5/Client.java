package com.prospring5;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.prospring5.service.CustomerServiceImpl;
import com.prospring5.util.SpringConfiguration;

public class Client {
	public static void main(String[] args) {
		CustomerServiceImpl customerService = null;
		
		AbstractApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfiguration.class);
		
		customerService = (CustomerServiceImpl)ctx.getBean("customerService");
		System.out.println(customerService.createCustomer());
		System.out.println(customerService.fetchCustomer());
		
		ctx.close();
		
	}
}
