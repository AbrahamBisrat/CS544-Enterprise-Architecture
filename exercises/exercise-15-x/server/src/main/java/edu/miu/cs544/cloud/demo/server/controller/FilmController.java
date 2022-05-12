package edu.miu.cs544.cloud.demo.server.controller;


import edu.miu.cs544.cloud.demo.server.aspect.ExecutionTime;
import edu.miu.cs544.cloud.demo.server.domain.Film;
import edu.miu.cs544.cloud.demo.server.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/films")
public class FilmController {

    private final FilmService filmService;

    @Autowired public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @ExecutionTime
    @GetMapping("/")
    public List<Film> findAll() {
        return filmService.findAll();
    }

    @ExecutionTime
    @GetMapping("/{id}")
    public Optional<Film> findFilmById(@PathVariable Long id) {
        return filmService.findFilmById(id);
    }

    @ExecutionTime
    @GetMapping("/title/{title}")
    public Optional<Film> findFilmByTitle(@PathVariable String title) {
        return filmService.findFilmByTitle(title);
    }

}
