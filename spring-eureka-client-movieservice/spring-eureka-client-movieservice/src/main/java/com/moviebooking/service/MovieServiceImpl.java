package com.moviebooking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moviebooking.dao.MovieDAO;
import com.moviebooking.model.Movie;
import com.moviebooking.model.Show;
import com.moviebooking.validator.Validator;

import java.util.List;

import javax.transaction.Transactional;

@Service(value="movieService")
@Transactional
public class MovieServiceImpl implements MovieService{
	@Autowired
	private MovieDAO movieDAO;
	
	@Override
	public List<Movie> getAllMovies() throws Exception{
		List<Movie> movies=movieDAO.getAllMovies();
		if(movies==null) {
			throw new Exception("MovieService.VIEW_MOVIES_FAILED");
		}
		return movies;
	}
	
	@Override
	public Integer addMovie(Movie movie) throws Exception{
		Validator.validateMovie(movie);
		Integer movieId=movieDAO.addMovie(movie);
		if(movieId==null) {
			throw new Exception("MovieService.ADD_MOVIE_FAILED");
		}
		return movieId;
	}
	
	@Override
	public Integer deleteMovie(Integer movieId) throws Exception{
		Integer id=movieDAO.deleteMovie(movieId);
		if(id==null) {
			throw new Exception("MovieService.DELETE_MOVIE_FAILED");
		}
		return id;
	}
	
	@Override
	public Integer updateMovie(Movie movie) throws Exception{
		Integer id=movieDAO.updateMovie(movie);
		if(id==null) {
			throw new Exception("MovieService.UPDATE_MOVIE_FAILED");
		}
		return id;
	}
	@Override
	public Integer deleteMovieFromTheatre(Integer theatreId,Integer movieId) throws Exception {
		Integer id=movieDAO.deleteMovieFromTheatre(theatreId, movieId);
		if(id==null) {
			throw new Exception("MovieService.DELETE_FROM_THEATRE_FAILED");
		}
		return id;
	}

	@Override
	public Integer addMovieToTheatre(Show show) throws Exception{
		Integer movieId=movieDAO.addMovieToTheatre(show);
		if(movieId==null) {
			throw new Exception("MovieService.ADD_MOVIE_TO_THEATRE_FAILED");
		}
		return movieId;
	}

}
