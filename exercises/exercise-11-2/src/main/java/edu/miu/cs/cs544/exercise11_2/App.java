package edu.miu.cs.cs544.exercise11_2;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App
{
	public static void main(String[] args) {
		ConfigurableApplicationContext context =
				new ClassPathXmlApplicationContext("springconfig.xml");

		IProductService productService =
				context.getBean("productService", IProductService.class);

		IInventoryService inventoryService =
				context.getBean("inventoryService", IInventoryService.class);

		Product product1 = productService.getProduct(423);
		if (product1 != null) {
			System.out.println(product1);
		}
		Product product2 = productService.getProduct(239);
		if (product2 != null) {
			System.out.println(product2);
		}

		System.out.println("we have " + productService.getNumberInStock(423)
				+ " product(s) with productNumber 423 in stock");
		System.out.println("we have " + productService.getNumberInStock(239)
				+ " product(s) with productNumber 239 in stock");

	}
}
