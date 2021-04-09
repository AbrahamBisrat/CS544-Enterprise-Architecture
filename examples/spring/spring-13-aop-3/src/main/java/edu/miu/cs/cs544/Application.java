package edu.miu.cs.cs544;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Application {
	private static ApplicationContext context;

	public static void main(String[] args) {
		context = new ClassPathXmlApplicationContext("springconfig.xml");

		AccountService accountService = context.getBean("accountService", AccountService.class);
		
		try{
			accountService.deposit(1234L, 2000);	
			accountService.deposit(4321L, 1000);
		} catch(Exception e) {
			// Do nothing! This is not a great idea, for demo only. 
			// Don't try at home!!! :-)
		}
		
	}
}