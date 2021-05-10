package edu.miu.cs.cs544.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import edu.miu.cs.cs544.model.Country;

@Service
public class CountryServiceClient {

	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${base-service-url}")
	private String baseUrl;
	
	public Country findById(String countryCode) {
		String url = baseUrl + "/countries/" + countryCode;
		return restTemplate
			.getForObject(url, 
					Country.class);
	}
	
	public List<Country> findAll() {
		Country[] countries = restTemplate
			.getForObject(baseUrl + "/countries", 
					Country[].class);
		
		return Arrays.asList(countries);
	}
	
}
