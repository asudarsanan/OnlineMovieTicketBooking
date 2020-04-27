package com.moviebooking.service;


import java.util.List;

import com.moviebooking.model.Theatre;

public interface TheatreService {

	public Integer addTheatre(Theatre theatre) throws Exception;

	public Integer deleteTheatre(Integer theatreId) throws Exception;

	public Integer updateTheatre(Theatre theatre) throws Exception;

	public List<Theatre> getAllTheatres() throws Exception;

	public List<Theatre> viewTheatreWithMovie(Integer movieId,String userDate) throws Exception;

	public List<String> viewShowTime(Integer movieId, Integer theatreId) throws Exception;

	public Integer showRemainingSeats(Integer showId) throws Exception;

}
