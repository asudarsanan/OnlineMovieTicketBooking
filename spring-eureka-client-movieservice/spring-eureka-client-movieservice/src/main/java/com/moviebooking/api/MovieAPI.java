package com.moviebooking.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import com.moviebooking.model.Movie;
import com.moviebooking.model.Show;
import com.moviebooking.service.MovieService;


@RestController
@RequestMapping(value="/movie")
public class MovieAPI {
	@Autowired
	Environment environment;

	@Autowired
	MovieService movieService;
	
	@GetMapping(value="/getAllMovies")
	public ResponseEntity<List<Movie>> getAllMovies() throws Exception{
		List<Movie> movies=null;
		try {
			movies=movieService.getAllMovies();
			return new ResponseEntity<List<Movie>>(movies, HttpStatus.OK);
		}
		catch (Exception e){
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, environment.getProperty(e.getMessage()));
		}
	}
	
	@PostMapping(value="/addMovie")
	public ResponseEntity<String> addMovie( @RequestBody Movie movie) throws Exception{
		try{
			Integer id=movieService.addMovie(movie);
			
			String str=environment.getProperty("MovieAPI.ADDED_SUCCESSFULLY")+id;
			ResponseEntity<String> response=new ResponseEntity<String>(str, HttpStatus.OK);
			return response;
		}
		catch(Exception e){
			throw new ResponseStatusException(HttpStatus.CONFLICT, environment.getProperty(e.getMessage()), e);
		}

	}
	
	
	@DeleteMapping(value="/deleteMovie/{movieId}")
	public ResponseEntity<String> deleteMovie(@PathVariable("movieId") Integer movieId) throws Exception{
		try {
			Integer id=movieService.deleteMovie(movieId);
			String str=environment.getProperty("MovieAPI.DELETED_SUCCESSFULLY")+id;
			ResponseEntity<String> response=new ResponseEntity<String>(str, HttpStatus.OK);
			return response;
		}
		catch(Exception e){
			throw new ResponseStatusException(HttpStatus.CONFLICT, environment.getProperty(e.getMessage()), e);
		}
	}
	
	@PostMapping(value="/updateMovie")
	public ResponseEntity<String> updateMovie(@RequestBody Movie movie){
		try{
			Integer id =movieService.updateMovie(movie);
			String successMessage=environment.getProperty("MovieAPI.UPDATE_SUCCESS")+id;
			ResponseEntity<String> response=new ResponseEntity<String>(successMessage, HttpStatus.OK);
			return response;
		}
		catch(Exception e){
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, environment.getProperty(e.getMessage()), e);
		}
		
	}
	
	@DeleteMapping(value="/deleteMovieFromTheatre/{movieId}/{theatreId}")
	public ResponseEntity<String> deleteMovieFromTheatre(@PathVariable("movieId") Integer movieId, @PathVariable("theatreId") Integer theatreId){
		try {
			Integer id=movieService.deleteMovieFromTheatre(theatreId, movieId);
			String str=environment.getProperty("MovieAPI.DELETED_SUCCESSFULLY")+id;
			ResponseEntity<String> response=new ResponseEntity<String>(str, HttpStatus.OK);
			return response;
		}
		catch(Exception e){
			throw new ResponseStatusException(HttpStatus.CONFLICT, environment.getProperty(e.getMessage()), e);
		}
	}
	
	@PostMapping(value="/addMovieToTheatre")
	public ResponseEntity<String> addMovie( @RequestBody Show show) throws Exception{
		try{
			Integer id=movieService.addMovieToTheatre(show);
			
			String str=environment.getProperty("MovieAPI.ADDED_TO_THEATRE_SUCCESS")+id;
			ResponseEntity<String> response=new ResponseEntity<String>(str, HttpStatus.OK);
			return response;
		}
		catch(Exception e){
			throw new ResponseStatusException(HttpStatus.CONFLICT, environment.getProperty(e.getMessage()), e);
		}

	}
	

}
