package com.prospring5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.prospring5.dao.AccountDAO;

@SpringBootApplication
public class Demo10Application {

	public static void main(String[] args) {
		// read the spring config java class
		AnnotationConfigApplicationContext context = (AnnotationConfigApplicationContext)SpringApplication.run(DemoConfig.class, args);
		
		// get the bean from spring container
		AccountDAO theAccountDAO = context.getBean("accountDAO", AccountDAO.class);
		
		// call the business method
		theAccountDAO.addAccount();
		
		// do it again!
		System.out.println("\nlet's call it again!\n");
		
		// call the business method again
		theAccountDAO.addAccount();
		
		// close the context
		context.close();
	}

}
