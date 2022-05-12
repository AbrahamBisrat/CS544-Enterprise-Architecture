package edu.miu.cs544.cloud.client.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import edu.miu.cs544.cloud.client.client.model.Actor;
import edu.miu.cs544.cloud.client.client.service.ActorServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.List;

@SpringBootApplication
public class ClientApplication {

    private ObjectMapper objectMapper;

    @Autowired public ClientApplication(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

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

//    @Bean @Primary public ObjectMapper objectMapper(Jackson2ObjectMapperBuilder builder) {
//        ObjectMapper objectMapper = builder.build();
//        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
//        return objectMapper;
//    }

    @PostConstruct public void setUp() {
        objectMapper.registerModule(new JavaTimeModule());
    }

}
