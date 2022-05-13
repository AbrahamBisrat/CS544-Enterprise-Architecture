package edu.miu.cs544.cloud.demo.server.controller;


import edu.miu.cs544.cloud.demo.server.aspect.ExecutionTime;
import edu.miu.cs544.cloud.demo.server.domain.Film;
import edu.miu.cs544.cloud.demo.server.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
    @ResponseStatus(HttpStatus.OK)
    public List<Film> findAll() {
        return filmService.findAll();
    }

    @ExecutionTime
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Film> findFilmById(@PathVariable Long id) {
        return filmService.findFilmById(id);
    }

    @ExecutionTime
    @GetMapping("/title/{title}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<Film> findFilmByTitle(@PathVariable String title) {
        return filmService.findFilmByTitle(title);
    }

}
