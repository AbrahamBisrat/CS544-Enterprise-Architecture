package edu.miu.cs.cs544.exercise15x.service.Impl;

import edu.miu.cs.cs544.exercise15x.domain.Actor;
import edu.miu.cs.cs544.exercise15x.repository.ActorRepo;
import edu.miu.cs.cs544.exercise15x.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ActorServiceImpl implements ActorService {

    private ActorRepo actorRepo;

    @Autowired
    public ActorServiceImpl(ActorRepo actorRepo) {
        this.actorRepo = actorRepo;
    }

    @Override public List<Actor> findAll() {
        return actorRepo.findAll();
    }

    @Override public Optional<Actor> findActorById(int id) {
        return Optional.of(actorRepo.findActorById(id)).orElse(null);
    }

    @Override public Optional<Actor> findActorByFirstName(String firstName) {
        return Optional.of(actorRepo.findActorByFirstName(firstName)).orElse(null);
    }
}
