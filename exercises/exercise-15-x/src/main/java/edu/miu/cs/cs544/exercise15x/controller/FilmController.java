package edu.miu.cs.cs544.exercise15x.controller;


import edu.miu.cs.cs544.exercise15x.domain.Film;
import edu.miu.cs.cs544.exercise15x.service.FilmService;
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

    private FilmService filmService;

    @Autowired
    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping("/")
    public List<Film> findAll() {
        return filmService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Film> findFilmById(@PathVariable Long id) {
        return filmService.findFilmById(id);
    }

    @GetMapping("/title/{title}")
    public Optional<Film> findFilmByTitle(@PathVariable String title) {
        return filmService.findFilmByTitle(title);
    }

}
