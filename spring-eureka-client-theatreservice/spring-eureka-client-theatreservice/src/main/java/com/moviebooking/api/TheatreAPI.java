package com.moviebooking.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;


import com.moviebooking.model.Theatre;
import com.moviebooking.service.TheatreService;

@RestController
@RequestMapping(value="/theatre")
public class TheatreAPI {
	@Autowired
	Environment environment;
	
	@Autowired
	private TheatreService theatreService;
	
	@GetMapping(value="/getAllTheatres")
	public ResponseEntity<List<Theatre>> getAllTheatres() throws Exception{
		List<Theatre> theatres=null;
		try {
			theatres=theatreService.getAllTheatres();
			return new ResponseEntity<List<Theatre>>(theatres, HttpStatus.OK);
		}
		catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, environment.getProperty(e.getMessage()));
		}
	}
	
	@PostMapping(value="/addTheatre")
	public ResponseEntity<String> addTheatre( @RequestBody Theatre theatre) throws Exception{
		try{
			Integer id=theatreService.addTheatre(theatre);
			
			String str=environment.getProperty("TheatreAPI.ADDED_SUCCESSFULLY")+id;
			ResponseEntity<String> response=new ResponseEntity<String>(str, HttpStatus.OK);
			return response;
		}
		catch(Exception e){
			throw new ResponseStatusException(HttpStatus.CONFLICT, environment.getProperty(e.getMessage()), e);
		}

	}
	
	@DeleteMapping(value="/deleteTheatre/{theatreId}")
	public ResponseEntity<String> deleteTheatre(@PathVariable("theatreId") Integer theatreId) throws Exception{
		try {
			Integer id=theatreService.deleteTheatre(theatreId);
			String str=environment.getProperty("TheatreAPI.DELETED_SUCCESSFULLY")+id;
			ResponseEntity<String> response=new ResponseEntity<String>(str, HttpStatus.OK);
			return response;
		}
		catch(Exception e){
			throw new ResponseStatusException(HttpStatus.CONFLICT, environment.getProperty(e.getMessage()), e);
		}
	}
	
	@PostMapping(value="/updateTheatre")
	public ResponseEntity<String> updateTheatre(@RequestBody Theatre theatre){
		try{
			Integer id =theatreService.updateTheatre(theatre);
			String successMessage=environment.getProperty("TheatreAPI.UPDATE_SUCCESS")+id;
			ResponseEntity<String> response=new ResponseEntity<String>(successMessage, HttpStatus.OK);
			return response;
		}
		catch(Exception e){
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, environment.getProperty(e.getMessage()), e);
		}
		
	}
	
	@GetMapping(value="/viewTheatresWithMovie/{movieId}/{userDate}")
	public ResponseEntity<List<Theatre>> viewTheatresWithMovie(@PathVariable Integer movieId,@PathVariable("userDate") String userDate) throws Exception{
		List<Theatre> theatres=null;
		try {
			theatres=theatreService.viewTheatreWithMovie(movieId,userDate);
			return new ResponseEntity<List<Theatre>>(theatres, HttpStatus.OK);
		}
		catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, environment.getProperty(e.getMessage()));
		}
	}
	
	@GetMapping(value="/viewShowTime/{movieId}/{theatreId}")
	public ResponseEntity<List<String>> viewShowTime(@PathVariable("movieId") Integer movieId,@PathVariable("theatreId") Integer theatreId) throws Exception{
		List<String> showTimeList=null;
		try {
			showTimeList=theatreService.viewShowTime(movieId, theatreId);
			return new ResponseEntity<List<String>>(showTimeList, HttpStatus.OK);
		}
		catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, environment.getProperty(e.getMessage()));
		}
	}
	
	@GetMapping(value="/viewRemainingSeats/{showId}")
	public ResponseEntity<String> showRemainingSeats(@PathVariable("showId") Integer showId) throws Exception{
		Integer remSeats=null;
		try {
			remSeats=theatreService.showRemainingSeats(showId);
			String successMessage=environment.getProperty("TheatreAPI.VIEW_SEATS_REMAINING_SUCCESS")+remSeats;
			ResponseEntity<String> response=new ResponseEntity<String>(successMessage, HttpStatus.OK);
			return response;
		}
		catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, environment.getProperty(e.getMessage()));
		}
		
	}

}
