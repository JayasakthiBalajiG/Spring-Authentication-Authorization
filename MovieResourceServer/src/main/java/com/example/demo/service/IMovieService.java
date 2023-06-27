package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import com.example.demo.model.Movie;

public interface IMovieService {

	
	public List<Movie> findAllMovies() ;

	public Optional<Movie> findMovieById(int id);
	
	public Movie findByMovieName(String MovieName) ;

}
