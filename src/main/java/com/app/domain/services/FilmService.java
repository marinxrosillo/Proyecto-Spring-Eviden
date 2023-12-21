package com.app.domain.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.app.persistence.crud.FilmRepository;
import com.app.persistence.entities.Client;
import com.app.persistence.entities.Film;

@Service
public class FilmService {

    @Autowired
    private FilmRepository filmRepository;

    public List<Film> getAllFilms() {
        return this.filmRepository.findAll();
    }

    public Optional<Film> getFilmById(Long id) {
        return this.filmRepository.findById(id);
    }

    public ResponseEntity<Film> createFilm(Film film) {
        Film newFilm = this.filmRepository.save(film);
        return ResponseEntity.status(HttpStatus.CREATED).body(newFilm);
    }

    public ResponseEntity<Film> updateFilm(Long id, Film film) {
        Optional<Film> optionalFilm = this.filmRepository.findById(id);

        if (optionalFilm.isPresent()) {
            Film existingFilm = optionalFilm.get();
            existingFilm.setTitle(film.getTitle());
            existingFilm.setDirector(film.getDirector());
            existingFilm.setAvailable(film.isAvailable());

            Film updatedFilm = this.filmRepository.save(existingFilm);
            return ResponseEntity.ok(updatedFilm);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public void deleteFilm(Long id) {
        this.filmRepository.deleteById(id);
    }
}