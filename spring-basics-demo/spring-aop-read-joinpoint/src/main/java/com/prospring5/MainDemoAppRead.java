package com.prospring5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.prospring5.dao.AccountDAO;
import com.prospring5.dao.MembershipDAO;

@SpringBootApplication
public class MainDemoAppRead {

	public static void main(String[] args) {
		// read spring config java class
		AnnotationConfigApplicationContext context = (AnnotationConfigApplicationContext) SpringApplication
				.run(DemoConfig.class, args);

		// get the bean from the spring container
		AccountDAO theAccountDAO = context.getBean("accountDAO", AccountDAO.class);

		// get the membership bean from the spring container
		MembershipDAO theMembershipDAO = context.getBean("membershipDAO", MembershipDAO.class);

		// call the business methods
		Account myAccount = new Account();
		myAccount.setName("Madhu");
		myAccount.setLevel("Platinum");
		theAccountDAO.addAccount(myAccount, true);
		theAccountDAO.doWork();

		// call the accountDAO getter/setter methods
		theAccountDAO.setName("foobar");
		theAccountDAO.setServiceCode("silver");

		String name = theAccountDAO.getName();
		String code = theAccountDAO.getServiceCode();

		// call the membershipDAO busines methods
		theMembershipDAO.addSillyMember();
		theMembershipDAO.goToSleep();

		// close the context
		context.close();
	}

}
