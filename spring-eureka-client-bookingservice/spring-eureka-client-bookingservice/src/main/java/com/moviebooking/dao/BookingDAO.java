package com.moviebooking.dao;


import java.util.List;

import com.moviebooking.model.Booking;
import com.moviebooking.model.Report;
import com.moviebooking.model.SeatAvailability;

public interface BookingDAO {

	public Integer bookTicket(Booking booking);

	public List<Booking> getMyBooking(String userEmailid);

	public Integer cancelBooking(Integer bookingId);

	public List<Report> generateBookingRecord(String startDate, String endDate);

	public List<Report> generateCancelRecord(String startDate, String endDate);

}
