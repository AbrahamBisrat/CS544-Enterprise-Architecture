package edu.miu.cs.cs544;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import edu.miu.cs.cs544.domain.Country;
import edu.miu.cs.cs544.repository.CountryRepository;

@Component
public class StartupRunner implements CommandLineRunner {
	
	@Autowired
	private CountryRepository countryRepository;
	
    @Override
    @Transactional
    public void run(String...args) throws Exception {
    	Country c1 = new Country("US", "United States", LocalDate.now());
    	Country c2 = new Country("CA", "Canada", LocalDate.now());
    	Country c3 = new Country("MX", "Mexico", LocalDate.now());
    	countryRepository.save(c1);
    	countryRepository.save(c2);
    	countryRepository.save(c3);
    }
}
