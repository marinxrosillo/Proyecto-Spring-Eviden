package com.app.webControllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.domain.services.FilmService;
import com.app.persistence.entities.Film;

@RestController
@RequestMapping("/api/films")
public class FilmController {

    @Autowired
    private FilmService filmService;

    @GetMapping
    public ResponseEntity<List<Film>> getAllFilms() {
        List<Film> films = filmService.getAllFilms();
        return new ResponseEntity<>(films, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Film> getFilmById(@PathVariable("id") Long id) {
        Optional<Film> film = this.filmService.getFilmById(id);
        return film.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Film> createFilm(@RequestBody Film film) {
        ResponseEntity<Film> response;
        ResponseEntity<Film> createdFilm = filmService.createFilm(film);
        
        if (createdFilm.getStatusCode().equals(HttpStatus.CREATED)) {
            response = new ResponseEntity<>(createdFilm.getBody(), HttpStatus.CREATED);
        } else {
            response = new ResponseEntity<>(createdFilm.getBody(), HttpStatus.BAD_REQUEST);
        }
        
        return response;
    }

    @PutMapping("/{id}")
    public ResponseEntity<Film> updateFilm(@PathVariable("id") Long id, @RequestBody Film film) {
        ResponseEntity<Film> response;
        ResponseEntity<Film> updatedFilm = filmService.updateFilm(id, film);
        
        if (updatedFilm.getStatusCode().equals(HttpStatus.OK)) {
            response = new ResponseEntity<>(updatedFilm.getBody(), HttpStatus.OK);
        } else {
            response = new ResponseEntity<>(updatedFilm.getBody(), HttpStatus.NOT_FOUND);
        }
        
        return response;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFilm(@PathVariable("id") Long id) {
        filmService.deleteFilm(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}