package com.prospring5;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.AbstractApplicationContext;

import com.prospring5.dto.CustomerDTO;
import com.prospring5.service.CustomerServiceImpl;

@SpringBootApplication
public class Demo6Application {

	public static void main(String[] args) {
		
		CustomerServiceImpl customerService = null;
		AbstractApplicationContext ctx =  (AbstractApplicationContext)SpringApplication.run(Demo6Application.class, args);
		customerService = (CustomerServiceImpl) ctx.getBean("customerService");
		List<CustomerDTO> listCust = customerService.fetchCustomer();
		System.out.println("PhoneNumber "+ "Name "+ "Email "+ "Address " );
		for(CustomerDTO customerDTO2: listCust) {
			System.out.format("%5d%10s%20s%10s", customerDTO2.getPhoneNo(), customerDTO2.getName(),
					customerDTO2.getEmail(), customerDTO2.getAddress());
			System.out.println();
		}
		ctx.close();
	}

}
