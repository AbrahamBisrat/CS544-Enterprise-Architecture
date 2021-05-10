package edu.miu.cs.cs544.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import edu.miu.cs.cs544.model.Country;

@Service
public class CountryServiceClient {

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private DiscoveryClient discoveryClient;

	@Value("${spring-boot-server.name}")
	private String serverName;
	
	public Country findById(String countryCode) {
		String url = getBaseServiceUrl() + "/countries/" + countryCode;
		return restTemplate
			.getForObject(url, 
					Country.class);
	}
	
	public List<Country> findAll() {
		Country[] countries = restTemplate
			.getForObject(getBaseServiceUrl() + "/countries", 
					Country[].class);
		
		return Arrays.asList(countries);
	}
	
	private String getBaseServiceUrl() {
		List<ServiceInstance> serviceInstances = discoveryClient.getInstances(serverName);
		serviceInstances.forEach(System.out::println);
	    return serviceInstances.get(0).getUri().toString();
	}
	
}
