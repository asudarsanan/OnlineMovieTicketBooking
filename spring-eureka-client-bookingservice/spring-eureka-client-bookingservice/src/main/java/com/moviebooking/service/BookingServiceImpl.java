package com.moviebooking.service;

import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.moviebooking.dao.BookingDAO;
import com.moviebooking.model.Booking;
import com.moviebooking.model.Report;
import com.moviebooking.model.SeatAvailability;
@Service(value="bookingService")
@Transactional
public class BookingServiceImpl implements BookingService{
	@Autowired
	private BookingDAO bookingDAO;
	
	
	@Override
	public Integer bookTicket(Booking booking) throws Exception{
		Integer bookingId=bookingDAO.bookTicket(booking);
		if(bookingId==null) {
			throw new Exception("BookingService.BOOK_TICKET_FAILED");
		}
		return bookingId;
	}
	
	@Override
	public List<Booking> getMyBooking(String userEmailid) throws Exception{
		List<Booking> bookingList=bookingDAO.getMyBooking(userEmailid);
		if(bookingList==null) {
			throw new Exception("BookingService.VIEW_BOOKING_FAILED");
		}
		return bookingList;
	}
	
	@Override
	public Integer cancelBooking(Integer bookingId) throws Exception{
		Integer id=bookingDAO.cancelBooking(bookingId);
		if(id==null) {
			throw new Exception("BookingService.CANCEL_TICKET_FAILED");
		}
		return id;
	}
	
	@Override 
	public List<Report> generateBookingRecord(String startDate,String endDate) throws Exception{
		List<Report> totBookingList=bookingDAO.generateBookingRecord(startDate,endDate);
		if(totBookingList==null) {
			throw new Exception("BookingService.GENERATE_BOOKING_REPORT_FAILED");
		}
		return totBookingList;
	}
	
	@Override 
	public List<Report> generateCancelRecord(String startDate,String endDate) throws Exception{
		List<Report> totCancelList=bookingDAO.generateCancelRecord(startDate, endDate);
		if(totCancelList==null) {
			throw new Exception("BookingService.GENERATE_CANCEL_REPORT_FAILED");
		}
		return totCancelList;
	}
	

}
