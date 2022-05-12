package edu.miu.cs544.cloud.demo.server.service.Impl;

import edu.miu.cs.cs544.exercise15x.domain.Actor;
import edu.miu.cs.cs544.exercise15x.models.ActorDto;
import edu.miu.cs.cs544.exercise15x.repository.ActorRepo;
import edu.miu.cs.cs544.exercise15x.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ActorServiceImpl implements ActorService {

    private final ActorRepo actorRepo;

    @Autowired public ActorServiceImpl(ActorRepo actorRepo) {
        this.actorRepo = actorRepo;
    }

    // make this method level transaction - readOnly Tx
    @Override public List<Actor> findAll() {
        return actorRepo.findAll();
    }

    @Override public Optional<Actor> findActorById(Long id) {
        return Optional.of(actorRepo.findActorById(id)).orElse(null);
    }

    @Override public Optional<Actor> findActorByFirstName(String firstName) {
        return Optional.of(actorRepo.findActorByFirstName(firstName)).orElse(null);
    }

    @Override public void update(ActorDto actorDto) {
        actorRepo.save(actorDto);
    }

}
