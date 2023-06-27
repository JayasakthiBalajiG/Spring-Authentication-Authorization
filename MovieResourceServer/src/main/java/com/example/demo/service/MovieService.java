package com.example.demo.service;


import com.example.demo.model.Movie;
import com.example.demo.repo.MovieRepository;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class MovieService implements IMovieService {

	@Autowired
	private MovieRepository movieRepo;

	@Override
	public List<Movie> findAllMovies() {
		return movieRepo.findAll();
	}

	@Override
	public Optional<Movie> findMovieById(int id) {
		return movieRepo.findById(id);
	}

	@Override
	public Movie findByMovieName(String MovieName) {
		Movie Movie=movieRepo.findByMovieName(MovieName);
		return Movie;
	}

	public Movie saveMovie(Movie newMovie) {
		
		Movie Movie=movieRepo.save(newMovie);
		return Movie;
		
	}

	public Movie updateMovie(int id,Movie movie) {
		
		Optional<Movie> retrievedMovie=movieRepo.findById(id);
		
		if(retrievedMovie==null)
			try {
				throw new Exception("Movie not found");
			} catch (Exception e) {
				e.printStackTrace();
			}
		movieRepo.save(movie);
		return movieRepo.findById(id).get();
		
	}
	
	public Movie deleteMovie(int MovieId) {
		
		Optional<Movie> retrievedMovie=movieRepo.findById(MovieId);
		if(retrievedMovie==null)
			try {
				throw new Exception("Movie not found");
			} catch (Exception e) {
				e.printStackTrace();
			}
		movieRepo.deleteById(MovieId);
		return retrievedMovie.get();
	}
}
