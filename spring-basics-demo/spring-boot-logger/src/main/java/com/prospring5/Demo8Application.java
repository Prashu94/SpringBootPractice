package com.prospring5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.AbstractApplicationContext;

import com.prospring5.service.CustomerServiceImpl;

@SpringBootApplication
public class Demo8Application {

	public static void main(String[] args) throws Exception {
		CustomerServiceImpl customerService = null;
		AbstractApplicationContext ctx = (AbstractApplicationContext)SpringApplication.run(Demo8Application.class, args);
		customerService = (CustomerServiceImpl)ctx.getBean("customerService");
		customerService.deleteCustomer(1151212222l);
		ctx.close();
	}

}
