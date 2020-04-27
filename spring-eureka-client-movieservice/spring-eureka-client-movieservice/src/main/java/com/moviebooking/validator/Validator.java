package com.moviebooking.validator;

import com.moviebooking.model.Movie;

public class Validator {
	
	public static void validateMovie(Movie movie) throws Exception{
		if(!validateMovieName(movie.getMovieName())) {
			throw new Exception("MovieValidator.INVALID_MOVIE_NAME");
		}
		if(!validateMovieDirector(movie.getMovieDirector())) {
			throw new Exception("MovieValidator.INVALID_MOVIE_DIRECTOR");
		}
		if(!validateMovieType(movie.getMovieType())){
			throw new Exception("MovieValidator.INVALID_MOVIE_TYPE");
		}
		if(!validateMovieLang(movie.getMovieLang())){
			throw new Exception("MovieValidator.INVALID_MOVIE_LANGUAGE");
		}
	}
	
	public static Boolean validateMovieName(String name){
		Boolean flag = false;
		if(!name.matches("[ ]*") && name.matches("([A-Za-z0-9])+(\\s[A-Za-z0-9]+)*"))
			flag=true;
		return flag;
	}
	
	public static Boolean validateMovieType(String type){
		Boolean flag = false;
		if(!type.matches("[ ]*") && type.matches("([A-Za-z/])+(\\s[A-Za-z/]+)*"))
			flag=true;
		return flag;
	}
	
	public static Boolean validateMovieDirector(String director){
		Boolean flag = false;
		if(!director.matches("[ ]*") && director.matches("([A-Za-z])+(\\s[A-Za-z]+)*"))
			flag=true;
		return flag;
	}
	
	public static Boolean validateMovieLang(String language){
		Boolean flag = false;
		if(!language.matches("[ ]*") && language.matches("([A-Za-z])+(\\s[A-Za-z]+)*"))
			flag=true;
		return flag;
	}

}
