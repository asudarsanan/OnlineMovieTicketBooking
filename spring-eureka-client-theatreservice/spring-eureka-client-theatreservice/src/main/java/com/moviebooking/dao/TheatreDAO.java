package com.moviebooking.dao;

import java.time.LocalDate;
import java.util.List;

import com.moviebooking.model.Show;
import com.moviebooking.model.Theatre;

public interface TheatreDAO {

	public Integer addTheatre(Theatre theatre);

	public Integer deleteTheatre(Integer theatreId);

	public Integer updateTheatre(Theatre theatre);

	public List<Theatre> getAllTheatres();

	public List<Theatre> viewTheatresWithMovie(Integer movieId,String userDate);

	public List<String> viewShowTime(Integer movieId, Integer theatreId);

	public Integer showRemainingSeats(Integer showId);

}
