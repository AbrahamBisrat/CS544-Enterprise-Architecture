package edu.miu.cs544.cloud.demo.server.service;

import edu.miu.cs.cs544.exercise15x.domain.Film;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface FilmService {

    List<Film> findAll();

    Optional<Film> findFilmById(Long id);

    Optional<Film> findFilmByTitle(String title);

}
