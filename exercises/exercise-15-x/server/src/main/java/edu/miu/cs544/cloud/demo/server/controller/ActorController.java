package edu.miu.cs544.cloud.demo.server.controller;

import edu.miu.cs.cs544.exercise15x.aspect.ExecutionTime;
import edu.miu.cs.cs544.exercise15x.domain.Actor;
import edu.miu.cs.cs544.exercise15x.models.ActorDto;
import edu.miu.cs.cs544.exercise15x.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/actors")
public class ActorController {

    private final ActorService actorService;

    @Autowired
    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }

    @ExecutionTime
    @GetMapping("/")
    public List<Actor> getAllActors() {
        return actorService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Actor> findActorById(@PathVariable Long id) {
        return actorService.findActorById(id);
    }

    @ExecutionTime
    @GetMapping("/firstName/{firstName}")
    public Optional<Actor> findActorByFirstName(@PathVariable String firstName) {
        return actorService.findActorByFirstName(firstName);
    }

    @PutMapping()
    @ExecutionTime
    public void updateActor(@RequestBody ActorDto actorDto) {
        actorService.update(actorDto);
    }

}
