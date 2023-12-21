package com.app.persistence.crud;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.persistence.entities.Film;

@Repository
public interface FilmRepository extends JpaRepository<Film, Long> {
	
	/*
	public List<Film> findAll() {
		return (List<Film>) filmCrudRepository.findAll();
	}
	
	public Optional<Film> findById(long id) {
		return (Optional<Film>) filmCrudRepository.findById(id);
	}
	
	public Film save(Film film) {
		return filmCrudRepository.save(film);
	}
	
	public void deleteById(long id) {
		filmCrudRepository.deleteById(id);
	}
	*/
}