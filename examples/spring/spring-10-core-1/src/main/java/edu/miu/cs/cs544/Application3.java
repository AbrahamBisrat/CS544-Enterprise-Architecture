package edu.miu.cs.cs544;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Application3 {
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("springconfig2.xml");
		
		System.out.println("Spring context loaded");
		
		CustomerService customerService1 = context.getBean("customerService", CustomerService.class);
		CustomerService customerService2 = context.getBean("customerService", CustomerService.class);
		
		System.out.println("customerService1 = " + customerService1);
		System.out.println("first bean retrieved");

		System.out.println("customerService2 = " + customerService2);
		System.out.println("second bean retrieved");
	}
}