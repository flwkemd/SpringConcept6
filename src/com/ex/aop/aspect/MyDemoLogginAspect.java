package com.ex.aop.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyDemoLogginAspect {

	//this is where we add all of our related advices for logging
	
	// let's start with an @Before advice
	
	//@Before("execution(public void updateAccount())") - doesn't work
	//@Before("execution(public void addAccount())") - work all of addAccount method
	//@Before("execution(public void com.ex.aop.dao.AccountDAO.addAccount())") -- only one match
	//@Before("execution(public void add*())") - match all add* method
	//@Before("execution(void add*())") - match void type & add * method
	
	@Before("execution(* add*())") 
	public void beforeAddAccountAdvice() {
		
		System.out.println("\n=====>>> Executing @Before advice on addAcount()");
		
	}
	
}
