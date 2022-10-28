package com.prospring5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.prospring5.dao.AccountDAO;
import com.prospring5.dao.MembershipDAO;

@SpringBootApplication
public class MainDemoApp3 {

	public static void main(String[] args) {
		// read the spring config java class
		AnnotationConfigApplicationContext context = (AnnotationConfigApplicationContext)SpringApplication.run(DemoConfig.class, args);
		
		// get the bean from the spring container
		AccountDAO theAccountDAO = context.getBean("accountDAO", AccountDAO.class);
		
		// get membership bean from the spring container
		MembershipDAO theMembershipDAO = context.getBean("membershipDAO", MembershipDAO.class);
		
		// call the business method.
		theAccountDAO.addAccount();
		
		// call the membership business method.
		theMembershipDAO.addAccount();
		
		// close the context
		context.close();
	}

}
