package com.moviebooking.service;

import java.util.List;

import com.moviebooking.model.Movie;
import com.moviebooking.model.Show;

public interface MovieService {

	public Integer addMovie(Movie movie) throws Exception;

	public Integer deleteMovie(Integer movieId) throws Exception;

	public Integer updateMovie(Movie movie) throws Exception;

	public List<Movie> getAllMovies() throws Exception;

	public Integer deleteMovieFromTheatre(Integer theatreId, Integer movieId) throws Exception;

	public Integer addMovieToTheatre(Show show) throws Exception;

	
	

}
