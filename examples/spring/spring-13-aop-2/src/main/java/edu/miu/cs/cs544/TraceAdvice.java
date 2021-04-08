package edu.miu.cs.cs544;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
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

	@After("execution(* edu.miu.cs.cs544.AccountService.*(..))")
	public void traceaftermethod(JoinPoint joinpoint) {
		System.out.println("after execution of method " + joinpoint.getSignature().getName());
	}
}