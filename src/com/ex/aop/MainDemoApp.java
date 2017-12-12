package com.ex.aop;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.ex.aop.dao.AccountDAO;
import com.ex.aop.dao.MembershipDAO;

public class MainDemoApp {

	public static void main(String[] args) {
		
		// read spring config java class
		AnnotationConfigApplicationContext context =
				new AnnotationConfigApplicationContext(DemoConfig.class);
		
		// get the bean from spring container
		AccountDAO theAccountDAO = context.getBean("accountDAO", AccountDAO.class);
		
		// get membership bean from spring container
		MembershipDAO theMembershipDAO = context.getBean("membershipDAO", MembershipDAO.class);
		
		// call the business method
		Account myAccount = new Account();
		myAccount.setName("Jun");
		myAccount.setLevel("Platinum");
		
		theAccountDAO.addAccount(myAccount, true);
		theAccountDAO.doWork();
		
		// call the accountDao getter/setter methods
		theAccountDAO.setName("Jun");
		theAccountDAO.setServiceCode("silver");
		
		String name = theAccountDAO.getName();
		String code = theAccountDAO.getServiceCode();
		
		// call the membership business method
		//theMembershipDAO.addAccount();
		theMembershipDAO.addMember();
		theMembershipDAO.goToSleep();
		
		// do it again
		//System.out.println("\n let's call it again! \n");
		
		// call the business method again
		//theAccountDAO.addAccount();
		
		// close the context
		context.close();
		
	}

}
