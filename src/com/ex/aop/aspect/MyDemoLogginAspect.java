package com.ex.aop.aspect;

import java.util.List;
import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.ex.aop.Account;

@Aspect
@Component
@Order(2)
public class MyDemoLogginAspect {
	
	private Logger myLogger = Logger.getLogger(getClass().getName());
	
	@Around("execution(* com.ex.aop.service.*.getFortune(..))")
	public Object aroundGetFortune(
			ProceedingJoinPoint theProceedingJoinPoint) throws Throwable {
		
		// print out method we are advising on
			String method = theProceedingJoinPoint.getSignature().toShortString();
			myLogger.info("\n======>>> Executing @Around on method: "+method);

		// get begin timestamp
		long begin = System.currentTimeMillis();
			
		// now, let's execute the method
		Object result = theProceedingJoinPoint.proceed();
		
		// get end timestamp
		long end = System.currentTimeMillis();
		
		// compute duration and display it
		long duration = end - begin;
		myLogger.info("\n=====> Duraion: "+duration/1000.0+" seconds");
		
		return result;
	}
	
	@After("execution(* com.ex.aop.dao.AccountDAO.findAccount(..))")
	public void afterFinallyFindAccountsAdvice(JoinPoint theJoinPoint) {

		// print out which method we are advising on
		String method = theJoinPoint.getSignature().toShortString();
		myLogger.info("\n======>>> Executing @After (finally) on method: "+method);
		
	}
	
	@AfterThrowing(
			pointcut="execution(* com.ex.aop.dao.AccountDAO.findAccount(..))",
			throwing="theExc")
	public void afterThrowingFindAccountsAdvice(
			JoinPoint theJoinPoint, Throwable theExc) {
		
		// print out which method we are advising on
		String method = theJoinPoint.getSignature().toShortString();
		myLogger.info("\n======>>> Executing @AfterThrowing on method: "+method);
		
		// log the exception
		myLogger.info("======>>> The exception is: "+theExc);
	
	}
	
	// add a new advice for @AfterReturning on the findAccounts method
	
	@AfterReturning(
			pointcut="execution(* com.ex.aop.dao.AccountDAO.findAccount(..))",
			returning="result")
	public void afterReturningFindAccountsAdvice(
			JoinPoint theJoinPoint, List<Account> result) {
		
		// print out which method we are advising on
		String method = theJoinPoint.getSignature().toShortString();
		myLogger.info("\n======>>> Executing @AfterReturning on method: "+method);
	
		// print out the results of the method call
		myLogger.info("======>>> result is: "+result);
	
		// let's post-process the data ... let's modify it
		// convert the account names to uppercase
		convertAccountNamesToUpperCase(result);
	}
	
	private void convertAccountNamesToUpperCase(List<Account> result) {
		
		// loop through accounts
		for(Account temp : result) {
			
			// get uppercase version of name
			String theUpperName = temp.getName().toUpperCase();
			
			// update the name on the account
			temp.setName(theUpperName);
		}
		
	}


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
	public void beforeAddAccountAdvice(JoinPoint theJoinPoint) {
		myLogger.info("=====>>> Executing @Before advice on method");
	
		// display the method signature
		MethodSignature methodSig = (MethodSignature)theJoinPoint.getSignature();
		myLogger.info("Method: "+methodSig);
		
		// display method arguments
		
		// get args
		Object[] args = theJoinPoint.getArgs();
		
		// loop thru args
		for (Object temp : args) {
			myLogger.info(temp.toString());
		
			if(temp instanceof Account) {
				// downcast and print Account specific stuff
				Account theAccount = (Account)temp;
				
				myLogger.info("account name: "+theAccount.getName());
				myLogger.info("account level: "+theAccount.getLevel());
			}
		}
	}
	
//	//@Before("forDaoPackage()")
//	@Before("forDaoPackageNoGetterSetter()")
//	public void performApiAnalytics() {
//		myLogger.info("=====>>> Performing API analytics");
//	}

//	@Before("forDaoPackageNoGetterSetter()")
//	public void logToCloudAsync() {
//		myLogger.info("=====>>> Logging to Cloud in async fashion");
//	}
	
	
}
