package com.ex.aop;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.ex.aop.dao.AccountDAO;
import com.ex.aop.dao.MembershipDAO;
import com.ex.aop.service.TrafficFortuneService;

public class AroundDemoApp { 

	public static void main(String[] args) {
		
		// read spring config java class
		AnnotationConfigApplicationContext context =
				new AnnotationConfigApplicationContext(DemoConfig.class);
		
		// get the bean from spring container
		TrafficFortuneService theFortuneService = context.getBean("trafficFortuneService", TrafficFortuneService.class);
		
		System.out.println("\nMain Program : AroundDemoApp");
		System.out.println("Calling getFortune");
		String data = theFortuneService.getFortune();
		
		System.out.println("\nMy fortune is: "+data);
		System.out.println("Finished");
		
		// close the context
		context.close();
		
	}

}
