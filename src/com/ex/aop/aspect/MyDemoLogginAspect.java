package com.ex.aop.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(2)
public class MyDemoLogginAspect {

	//this is where we add all of our related advices for logging
	
	// let's start with an @Before advice
	
	//@Before("execution(public void updateAccount())") - doesn't work
	//@Before("execution(public void addAccount())") - work all of addAccount method
	//@Before("execution(public void com.ex.aop.dao.AccountDAO.addAccount())") -- only one match
	//@Before("execution(public void add*())") - match all add* method
	//@Before("execution(void add*())") - match void type & add * method
	//@Before("execution(* add*())") - match all type & add * method  
	//@Before("execution(* add*(com.ex.aop.Account))") - match one param
	//@Before("execution(* add*(com.ex.aop.Account, ..))") // match one param & any types
	//@Before("execution(* add*(..))") // match any params
	//@Before("execution(* com.ex.aop.dao.*.*(..))") // match package any class & method & params
	
//	@Pointcut("execution(* com.ex.aop.dao.*.*(..))")
//	private void forDaoPackage() {}
//	
//	// create pointcut for getter methods
//	@Pointcut("execution(* com.ex.aop.dao.*.get*(..))")
//	private void getter() {}
//	
//	// create pointcut for setter methods
//	@Pointcut("execution(* com.ex.aop.dao.*.set*(..))")
//	private void setter() {}
//	
//	// create pointcut : include package ... exclude getter/setter
//	@Pointcut("forDaoPackage() && !(getter() || setter())")
//	private void forDaoPackageNoGetterSetter() {}
	
	//@Before("forDaoPackage()")
	@Before("com.ex.aop.aspect.LuvAopExpressions.forDaoPackageNoGetterSetter()")
	public void beforeAddAccountAdvice() {
		System.out.println("=====>>> Executing @Before advice on method");
	}
	
//	//@Before("forDaoPackage()")
//	@Before("forDaoPackageNoGetterSetter()")
//	public void performApiAnalytics() {
//		System.out.println("=====>>> Performing API analytics");
//	}

//	@Before("forDaoPackageNoGetterSetter()")
//	public void logToCloudAsync() {
//		System.out.println("=====>>> Logging to Cloud in async fashion");
//	}
	
}
