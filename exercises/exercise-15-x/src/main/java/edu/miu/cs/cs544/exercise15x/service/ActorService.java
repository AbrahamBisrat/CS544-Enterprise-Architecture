package edu.miu.cs.cs544.exercise15x.service;

import edu.miu.cs.cs544.exercise15x.domain.Actor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ActorService {

    List<Actor> findAll();

    Optional<Actor> findActorById(int id);

    Optional<Actor> findActorByFirstName(String firstName);

}
