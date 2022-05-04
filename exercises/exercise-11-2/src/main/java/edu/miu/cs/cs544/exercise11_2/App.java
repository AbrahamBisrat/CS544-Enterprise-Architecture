package edu.miu.cs.cs544.exercise11_2;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App
{
	public static void main(String[] args) {
//		IProductService productService = new ProductService();
		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("springconfig.xml");


		IProductService productService =

		Product product1 = productService.getProduct(423);
		if (product1 != null) {
			System.out.println(product1);
		}
		Product product2 = productService.getProduct(239);
		if (product2 != null) {
			System.out.println(product2);
		}

	}
}
