package edu.miu.cs.cs544;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import edu.miu.cs.cs544.repository.CountryRepository;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(Application.class, args);
		
		CountryRepository repository = context.getBean(CountryRepository.class);
		
		repository.findAll().forEach(System.out::println);
	}

}
