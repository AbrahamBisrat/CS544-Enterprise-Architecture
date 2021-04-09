package edu.miu.cs.cs544;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Application {
	private static ApplicationContext context;

	public static void main(String[] args) {
		System.out.println("1");
		context = new ClassPathXmlApplicationContext("springconfig.xml");

		System.out.println("2");
		CustomerService customerService = context.getBean("customerService", CustomerService.class);
		
		System.out.println("3");
		customerService.addCustomer("Frank Brown");
		
		System.out.println("4");
	}
}