package com.moviebooking.dao;

import java.util.List;

//import java.security.NoSuchAlgorithmException;

import com.moviebooking.model.Movie;
import com.moviebooking.model.Show;

public interface MovieDAO {
	
	public Integer addMovie(Movie movie);

	public Integer deleteMovie(Integer movieId);

	public Integer updateMovie(Movie movie);

	public List<Movie> getAllMovies();

	public Integer deleteMovieFromTheatre(Integer theatreId, Integer movieId);

	public Integer addMovieToTheatre(Show show);

}
