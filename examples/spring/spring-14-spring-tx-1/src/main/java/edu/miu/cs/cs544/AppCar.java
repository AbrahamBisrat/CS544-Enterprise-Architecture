package edu.miu.cs.cs544;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AppCar {

    @SuppressWarnings("resource")
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("springconfig.xml");

		CustomerService customerService = context.getBean("customerService", CustomerService.class);
		
		customerService.createRecords();
		customerService.readRecords();
    }
}
