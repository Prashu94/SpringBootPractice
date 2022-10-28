package com.prospring5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.prospring5.dao.AccountDAO;
import com.prospring5.dao.MembershipDAO;

@SpringBootApplication
public class MainDemoAppPointCut {

	public static void main(String[] args) {
		// read the spring java config class
		AnnotationConfigApplicationContext context = (AnnotationConfigApplicationContext)SpringApplication.run(MainDemoAppPointCut.class, args);
		
		// get the bean from spring container
		AccountDAO theAccountDAO = context.getBean("accountDAO", AccountDAO.class);
		
		// get membership bean from the spring container
		MembershipDAO theMembershipDAO = context.getBean("membershipDAO", MembershipDAO.class);
		
		// call the business method
		Account myAccount = new Account();
		theAccountDAO.addAccount(myAccount, true);
		theAccountDAO.doWork();
		
		// call the membership business method
		theMembershipDAO.addSillyMember();
		theMembershipDAO.goToSleep();
		
		// close the context
		context.close();
	}

}
