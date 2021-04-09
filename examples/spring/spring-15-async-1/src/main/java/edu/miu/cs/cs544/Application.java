package edu.miu.cs.cs544;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Application {
	private static ApplicationContext context;

	public static void main(String[] args) {
		context = new ClassPathXmlApplicationContext("springconfig.xml");
		SampleClass sc = context.getBean("sampleClass", SampleClass.class);
		sc.longRunningMethod();
		System.out.println("Main method continues on!");
	}
}