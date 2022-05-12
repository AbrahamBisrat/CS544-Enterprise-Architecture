package edu.miu.cs544.cloud.client.client;

import edu.miu.cs544.cloud.client.client.model.Actor;
import edu.miu.cs544.cloud.client.client.service.ActorServiceClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@SpringBootApplication
public class ClientApplication {

    public static void main(String[] args) {
        ApplicationContext context =
                SpringApplication.run(ClientApplication.class, args);

        ActorServiceClient client = context.getBean(ActorServiceClient.class);

        Actor actor = client.getActorFromFarAwayById(1);
        System.out.println("actor = " + actor);

        List<Actor> actors = client.getAllActorsFromFarAway();
        actors.forEach(System.out::println);

    }

    @Bean public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
