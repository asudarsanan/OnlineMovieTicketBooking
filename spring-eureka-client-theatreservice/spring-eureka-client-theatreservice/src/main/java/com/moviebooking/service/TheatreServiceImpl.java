package com.moviebooking.service;


import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moviebooking.dao.TheatreDAO;

import com.moviebooking.model.Theatre;

@Service(value="theatreService")
@Transactional
public class TheatreServiceImpl implements TheatreService{
	
	@Autowired
	private TheatreDAO theatreDAO;
	
	@Override
	public List<Theatre> getAllTheatres() throws Exception{
		List<Theatre> theatres=theatreDAO.getAllTheatres();
		if(theatres==null) {
			throw new Exception("TheatreService.VIEW_THEATRES_FAILED");
		}
		return theatres;
	}
	
	@Override
	public Integer addTheatre(Theatre theatre) throws Exception{
		Integer theatreId=theatreDAO.addTheatre(theatre);
		if(theatreId==null) {
			throw new Exception("TheatreService.ADD_THEATRE_FAILED");
		}
		return theatreId;
	}
	
	@Override
	public Integer deleteTheatre(Integer theatreId) throws Exception{
		Integer id=theatreDAO.deleteTheatre(theatreId);
		if(id==null) {
			throw new Exception("TheatreService.DELETE_THEATRE_FAILED");
		}
		return id;
		
	}
	@Override
	public Integer updateTheatre(Theatre theatre) throws Exception{
		Integer theatreId=theatreDAO.updateTheatre(theatre);
		if(theatreId==null) {
			throw new Exception("TheatreService.UPDATE_THEATRE_FAILED");
		}
		return theatreId;
	}
	@Override
	public List<Theatre> viewTheatreWithMovie(Integer movieId,String userDate) throws Exception{
		List<Theatre> theatres=theatreDAO.viewTheatresWithMovie(movieId,userDate);
		if(theatres==null) {
			throw new Exception("TheatreService.THEATRES_NOT_AVAILABLE");
		}
		if(theatres.size()==0) {
			throw new Exception("TheatreService.NOT_AVAILABLE_ON_GIVEN_DATE");
		}
		return theatres;
	}
	@Override
	public List<String> viewShowTime(Integer movieId,Integer theatreId) throws Exception{
		List<String> showTimeList=theatreDAO.viewShowTime(movieId, theatreId);
		if(showTimeList==null) {
			throw new Exception("TheatreService.SHOW_TIME_NOT_AVAILABLE");
		}
		return showTimeList;
	}
	
	@Override
	public Integer showRemainingSeats(Integer showId) throws Exception {
		Integer remSeats=theatreDAO.showRemainingSeats(showId);
		if(remSeats==null) {
			throw new Exception("TheatreService.SHOW_SEATS_NOT_AVAILABLE");
		}
		return remSeats;
	}

}
