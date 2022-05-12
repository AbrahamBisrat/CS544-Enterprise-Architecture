package edu.miu.cs544.cloud.demo.server.repository;

import edu.miu.cs.cs544.exercise15x.domain.Film;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface FilmRepo extends CrudRepository<Film, Long> {

    @Override List<Film> findAll();

    @Override Optional<Film> findById(Long aLong);

    Optional<Film> findFilmByTitle(String title);

}