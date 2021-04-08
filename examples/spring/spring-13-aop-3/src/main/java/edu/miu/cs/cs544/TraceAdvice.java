package edu.miu.cs.cs544;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TraceAdvice {
	@Before("execution(* edu.miu.cs.cs544.AccountService.*(..)) && args(accountNumber, amount)")
	public void tracebeforemethod(JoinPoint joinpoint, long accountNumber, double amount) {
		System.out.print("before execution of method " + joinpoint.getSignature().getName());
		System.out.println(", Account# " + accountNumber + ", Amount = " + amount);
	}

	@AfterReturning(pointcut="execution(* edu.miu.cs.cs544.AccountService.*(..))", returning="result")
	public void traceaftermethodNormal(JoinPoint joinpoint, boolean result) {
		System.out.println("after normal execution of method " + joinpoint.getSignature().getName());
		System.out.println("execution was successful? " + result);
	}

	@AfterThrowing(pointcut="execution(* edu.miu.cs.cs544.AccountService.*(..))", throwing="exception")
	public void traceaftermethodException(JoinPoint joinpoint, Exception exception) {
		System.out.println("after execution of method " + joinpoint.getSignature().getName());
		System.out.println("following exception happenned " + exception);
	}
}