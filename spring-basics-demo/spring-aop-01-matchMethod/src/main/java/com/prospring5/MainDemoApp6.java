package com.prospring5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.prospring5.dao.AccountDAO;
import com.prospring5.dao.MembershipDAO;

@SpringBootApplication
public class MainDemoApp6 {

	public static void main(String[] args) {
		// read the spring Java Config class
		AnnotationConfigApplicationContext context = (AnnotationConfigApplicationContext) SpringApplication.run(DemoConfig.class, args);
		
		// get the bean from the spring container
		AccountDAO theAccountDAO = context.getBean("accountDAO", AccountDAO.class);
		
		// get the membership from the spring container
		MembershipDAO theMembershipDAO = context.getBean("membershipDAO", MembershipDAO.class);
		
		// call the business method
		Account myAccount = new Account();
		theAccountDAO.addAccount(myAccount, true);
		
		// call the membership method
		theMembershipDAO.addSillyMember();
		
		// close the context
		context.close();
	}

}
