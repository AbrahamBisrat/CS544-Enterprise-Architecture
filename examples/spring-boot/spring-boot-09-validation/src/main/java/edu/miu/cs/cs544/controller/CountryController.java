package edu.miu.cs.cs544.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.miu.cs.cs544.domain.Country;
import edu.miu.cs.cs544.repository.CountryRepository;

@RestController
@RequestMapping("/countries")
public class CountryController {

	@Autowired
	private CountryRepository countryRepository;

	@GetMapping
	public List<Country> findAll() {
		return countryRepository.findAll();
	}

	@GetMapping("/{id}")
	public Country findById(@PathVariable(name = "id") String countryId) {
		return countryRepository.findById(countryId).get();
	}

	@GetMapping(params = "paged=true")
	public Page<Country> findAll(Pageable pageable) {
		return countryRepository.findAll(pageable);
	}
	
	@PostMapping
	public Country createCountry(@Valid @RequestBody Country country) {
		countryRepository.findById(country.getCountryRegionCode())
			.ifPresent(c -> {
				throw new RuntimeException();
			});
		return countryRepository.save(country);
	}

	@PutMapping("/{id}")
	public Country updateCountry(@PathVariable(name = "id") String countryId, 
			@Valid @RequestBody Country country) {
		countryRepository.findById(countryId).orElseThrow(RuntimeException::new);
		
		country.setCountryRegionCode(countryId);

		return countryRepository.save(country);
	}
}
