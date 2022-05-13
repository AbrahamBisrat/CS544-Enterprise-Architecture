package edu.miu.cs544.cloud.client.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.List;

@EnableEurekaClient
@SpringBootApplication
public class ClientApplication {

//    private final ObjectMapper objectMapper;
//
//    @Autowired public ClientApplication(ObjectMapper objectMapper) {
//        this.objectMapper = objectMapper;
//    }
//
//    public static void mainX(String[] args) {
//        ApplicationContext context =
//                SpringApplication.run(ClientApplication.class, args);
//
//        ActorServiceClient client = context.getBean(ActorServiceClient.class);
//
//        Actor actor = client.getActorFromFarAwayById(1);
//        System.out.println("actor = " + actor);
//
//        List<Actor> actors = client.getAllActorsFromFarAway();
//        actors.forEach(System.out::println);
//
//    }

    public static void main(String[] args) {
        SpringApplication.run(ClientApplication.class, args);
    }

    @Bean public RestTemplate restTemplate() {
        return new RestTemplate();
    }

//    @PostConstruct public void setUp() {
//        objectMapper.registerModule(new JavaTimeModule());
//    }

}
