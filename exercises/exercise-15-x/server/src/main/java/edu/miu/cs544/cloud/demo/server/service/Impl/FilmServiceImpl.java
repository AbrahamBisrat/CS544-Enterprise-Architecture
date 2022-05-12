package edu.miu.cs544.cloud.demo.server.service.Impl;

import edu.miu.cs544.cloud.demo.server.domain.Film;
import edu.miu.cs544.cloud.demo.server.repository.FilmRepo;
import edu.miu.cs544.cloud.demo.server.service.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class FilmServiceImpl implements FilmService {

    private final FilmRepo filmRepo;

    @Autowired public FilmServiceImpl(FilmRepo filmRepo) {
        this.filmRepo = filmRepo;
    }

    @Override public List<Film> findAll() {
        return filmRepo.findAll();
    }

    @Override public Optional<Film> findFilmById(Long id) {
        return Optional.of(filmRepo.findById(id)).orElse(null);
    }

    @Override public Optional<Film> findFilmByTitle(String title) {
        return Optional.of(filmRepo.findFilmByTitle(title)).orElse(null);
    }

}
