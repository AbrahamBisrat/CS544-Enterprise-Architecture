package edu.miu.cs544.cloud.demo.server.service;

import edu.miu.cs.cs544.exercise15x.domain.Actor;
import edu.miu.cs.cs544.exercise15x.models.ActorDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ActorService {

    List<Actor> findAll();

    Optional<Actor> findActorById(Long id);

    Optional<Actor> findActorByFirstName(String firstName);

    void update(ActorDto actorDto);

}