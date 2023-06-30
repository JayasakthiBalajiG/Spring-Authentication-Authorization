package com.example.demo.controller;

import java.util.List;

import com.example.demo.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.service.MovieService;

@RestController
@RequestMapping("/v1")
public class MovieRestController {
	
	@Autowired
    MovieService movieService;

	  /**
        * This endpoint is responsible for displaying all the movies
        * URI: /v1/movies
        * HTTP method: GET
    	 */
	@GetMapping("/movies")
	public ResponseEntity<List<Movie>> getAllMovies() {
		return ResponseEntity.ok().body(movieService.findAllMovies());
	}

	 /**
        * This endpoint is responsible for inserting new movies
        * URI: /v1/movies
        * HTTP method: POST
    	 */
	@PostMapping("/movies")
	public ResponseEntity<Movie> saveMovies(@RequestBody Movie newMovie,Authentication auth) {
		return ResponseEntity.status(HttpStatus.CREATED).body((movieService.saveMovie(newMovie)));
	}

	 /**
        * This endpoint is responsible for displaying the movie respective to the id
        * URI: /v1/movies/{id}
        * HTTP method: GET
    	 */
	@GetMapping("/movies/{id}")
	public ResponseEntity<Movie> getMovieById(@PathVariable("id") int movieId) {
		return ResponseEntity.ok().body(movieService.findMovieById(movieId).get());
		
	}

	 /**
        * This endpoint is responsible for deleting movie respective to the id
        * URI: /v1/movies/{id}
        * HTTP method: DELETE
    	 */
	@DeleteMapping("/movies/{id}")
	public ResponseEntity<Object> deleteMovie(@PathVariable("id") int movieId) {
		 movieService.deleteMovie(movieId);
		 return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		
	}

}
