package com.ex.aop;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.ex.aop.dao.AccountDAO;
import com.ex.aop.dao.MembershipDAO;

public class AfterFinallyDemoApp {

	public static void main(String[] args) {
		
		// read spring config java class
		AnnotationConfigApplicationContext context =
				new AnnotationConfigApplicationContext(DemoConfig.class);
		
		// get the bean from spring container
		AccountDAO theAccountDAO = context.getBean("accountDAO", AccountDAO.class);
		
		// call method to find the accounts
		List<Account> theAccounts = null;
		
		try {
			// add a boolean flag to simulate exceptions
			boolean tripWire = false;
			theAccounts = theAccountDAO.findAccount(tripWire);
		}catch(Exception e){
			System.out.println("\nMain Program ... caught exception"+e);
		}
		
		
		// display the accounts
		System.out.println("\nMain Program: AfterThrowingDemoApp");
		System.out.println("--------");
		
		System.out.println(theAccounts);
		
		System.out.println();
		
		// close the context
		context.close();
		
	}

}
