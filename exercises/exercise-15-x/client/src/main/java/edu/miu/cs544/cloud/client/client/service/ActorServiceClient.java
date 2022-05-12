package edu.miu.cs544.cloud.client.client.service;

import edu.miu.cs544.cloud.client.client.model.Actor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class ActorServiceClient {

    private final RestTemplate restTemplate;

    @Autowired public ActorServiceClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Value("${base-service-url}")
    private String baseUrl;

    public List<Actor> getAllActorsFromFarAway() {
        return List.of(restTemplate
                .getForObject(baseUrl + "/actors/", Actor[].class));
    }

    public Actor getActorFromFarAwayById(int id) {
        return restTemplate.getForObject(baseUrl + "/actors/" + id, Actor.class);
    }

}
