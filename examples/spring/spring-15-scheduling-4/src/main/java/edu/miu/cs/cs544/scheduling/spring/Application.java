package edu.miu.cs.cs544.scheduling.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Application {
	@SuppressWarnings({ "resource", "unused" })
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("springconfig.xml");
	}
}
