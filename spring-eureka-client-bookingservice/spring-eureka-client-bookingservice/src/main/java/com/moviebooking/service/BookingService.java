package com.moviebooking.service;

import java.time.LocalDate;
import java.util.List;

import com.moviebooking.model.Booking;
import com.moviebooking.model.Report;
import com.moviebooking.model.SeatAvailability;

public interface BookingService {

	public Integer bookTicket(Booking booking) throws Exception;

	public List<Booking> getMyBooking(String userEmailid) throws Exception;

	public Integer cancelBooking(Integer bookingId) throws Exception;

	public List<Report> generateBookingRecord(String startDate, String endDate) throws Exception;

	public List<Report> generateCancelRecord(String startDate, String endDate) throws Exception;

}
