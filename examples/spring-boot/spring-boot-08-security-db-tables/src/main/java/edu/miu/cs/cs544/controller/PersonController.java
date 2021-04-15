package edu.miu.cs.cs544.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.miu.cs.cs544.domain.Person;
import edu.miu.cs.cs544.repository.PersonRepository;

@RestController
@RequestMapping("/people")
public class PersonController {
	
	@Autowired
	private PersonRepository personRepository;
	
	@GetMapping
	public Page<Person> findAll(Pageable pageable) {
		return personRepository.findAll(pageable);
	}
	
}
