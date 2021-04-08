package edu.miu.cs.cs544;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Application {
	public static void main(String[] args) {
		System.out.println("1");
		ApplicationContext context = new ClassPathXmlApplicationContext("springconfig.xml");

		System.out.println("2");
		CustomerService customerService = context.getBean("customerService", CustomerService.class);
		
		System.out.println("3");
		customerService.addCustomer("Frank Brown");
		
		System.out.println("4");
		((ClassPathXmlApplicationContext)context).destroy();

	}
}