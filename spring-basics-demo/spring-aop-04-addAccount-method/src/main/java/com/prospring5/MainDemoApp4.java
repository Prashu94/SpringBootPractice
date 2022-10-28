package com.prospring5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.prospring5.dao.AccountDAO;
import com.prospring5.dao.MembershipDAO;

@SpringBootApplication
public class MainDemoApp4 {

	public static void main(String[] args) {
		// read the spring config java class
		AnnotationConfigApplicationContext context = (AnnotationConfigApplicationContext)SpringApplication.run(DemoConfig.class, args);
				
		// get the bean from the container 
		AccountDAO theAccountDAO = context.getBean("accountDAO", AccountDAO.class);
		
		// get membership from the bean container
		MembershipDAO theMembershipDAO = context.getBean("membershipDAO", MembershipDAO.class);
		
		// call the business method
		theAccountDAO.addAccount();
		
		// call the membership business method
		theMembershipDAO.addSillyMember();
		
		// close the context
		context.close();
	}

}
