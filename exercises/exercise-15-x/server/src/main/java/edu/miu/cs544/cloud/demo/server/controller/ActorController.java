package edu.miu.cs544.cloud.demo.server.controller;

import edu.miu.cs544.cloud.demo.server.aspect.ExecutionTime;
import edu.miu.cs544.cloud.demo.server.domain.Actor;
import edu.miu.cs544.cloud.demo.server.model.ActorDto;
import edu.miu.cs544.cloud.demo.server.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/actors")
public class ActorController {

    private final ActorService actorService;

    @Autowired public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }

    @ExecutionTime
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/") // why ResponseBody? cause I want the entire HTTP response
    public ResponseEntity<List<Actor>> getAllActors() {
        return ResponseEntity.ok().body(actorService.findAll());
    }

    @GetMapping("/{id}")
    public Optional<Actor> findActorById(@PathVariable Long id) {
        return actorService.findActorById(id);
    }

    @ExecutionTime
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/firstName/{firstName}")
    public Optional<Actor> findActorByFirstName(@PathVariable String firstName) {
        return actorService.findActorByFirstName(firstName);
    }

    @ExecutionTime
    @PutMapping()
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void updateActor(@RequestBody ActorDto actorDto) {
        actorService.update(actorDto);
    }

}
