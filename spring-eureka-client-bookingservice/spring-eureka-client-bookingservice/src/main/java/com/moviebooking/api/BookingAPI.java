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

import com.moviebooking.model.Booking;
import com.moviebooking.model.Report;

import com.moviebooking.service.BookingService;

@RestController
@RequestMapping(value="/booking")
public class BookingAPI {
	@Autowired
	Environment environment;

	@Autowired
	BookingService bookingService;
	
	@PostMapping(value="/bookTicket")
	public ResponseEntity<String> addBooking( @RequestBody Booking booking) throws Exception{
		try{
			Integer id=bookingService.bookTicket(booking);
			String str=environment.getProperty("BookingAPI.BOOK_TICKETS_SUCCESS")+id;
			ResponseEntity<String> response=new ResponseEntity<String>(str, HttpStatus.OK);
			return response;
		}
		catch(Exception e){
			throw new ResponseStatusException(HttpStatus.CONFLICT, environment.getProperty(e.getMessage()), e);
		}

	}
		
	@GetMapping(value="/getMyBookings/{userEmailid}")
	public ResponseEntity<List<Booking>> getMyBooking(@PathVariable("userEmailid") String userEmailid) throws Exception{
		try {
			List<Booking> bookingList=bookingService.getMyBooking(userEmailid);
			return new ResponseEntity<List<Booking>>(bookingList, HttpStatus.OK);
		}
		catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, environment.getProperty(e.getMessage()));
		}
	}
	
	@DeleteMapping(value="/cancelBooking/{bookingId}")
	public ResponseEntity<String> cancelBooking(@PathVariable("bookingId") Integer bookingId) throws Exception{
		try {
			Integer id=bookingService.cancelBooking(bookingId);
			String str=environment.getProperty("BookingAPI.CANCEL_BOOKING_SUCCESS")+id;
			ResponseEntity<String> response=new ResponseEntity<String>(str, HttpStatus.OK);
			return response;
		}
		catch(Exception e){
			throw new ResponseStatusException(HttpStatus.CONFLICT, environment.getProperty(e.getMessage()), e);
		}
	}
	
	@GetMapping(value="/generateBookingRecord/{startDate}/{endDate}")
	public ResponseEntity<List<Report>> generateBookingRecord(@PathVariable("startDate") String startDate,@PathVariable("endDate") String endDate) throws Exception{
		try {
			List<Report> bookingList=bookingService.generateBookingRecord(startDate, endDate);
			return new ResponseEntity<List<Report>>(bookingList, HttpStatus.OK);
		}
		catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, environment.getProperty(e.getMessage()));
		}
	}
	
	@GetMapping(value="/generateCancelRecord/{startDate}/{endDate}")
	public ResponseEntity<List<Report>> generateCancelRecord(@PathVariable("startDate") String startDate,@PathVariable("endDate") String endDate) throws Exception{
		try {
			List<Report> cancelList=bookingService.generateCancelRecord(startDate, endDate);
			return new ResponseEntity<List<Report>>(cancelList, HttpStatus.OK);
		}
		catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, environment.getProperty(e.getMessage()));
		}
	}

}
