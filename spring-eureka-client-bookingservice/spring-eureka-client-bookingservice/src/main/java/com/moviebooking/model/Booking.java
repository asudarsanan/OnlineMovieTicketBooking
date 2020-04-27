package com.moviebooking.model;

import java.time.LocalDate;

public class Booking {
	private Integer bookingId;
	private Integer noOfTickets;
	private LocalDate bookingDate;
	private User user;
	private SeatAvailability seatAvailability;
	private Integer totalRate;
	private Movie movie;
	private Theatre theatre;
	private String bookingTime;
	
	
	public String getBookingTime() {
		return bookingTime;
	}
	public void setBookingTime(String bookingTime) {
		this.bookingTime = bookingTime;
	}
	public Movie getMovie() {
		return movie;
	}
	public void setMovie(Movie movie) {
		this.movie = movie;
	}
	public Theatre getTheatre() {
		return theatre;
	}
	public void setTheatre(Theatre theatre) {
		this.theatre = theatre;
	}
	public Integer getBookingId() {
		return bookingId;
	}
	public void setBookingId(Integer bookingId) {
		this.bookingId = bookingId;
	}
	public Integer getNoOfTickets() {
		return noOfTickets;
	}
	public void setNoOfTickets(Integer noOfTickets) {
		this.noOfTickets = noOfTickets;
	}
	public LocalDate getBookingDate() {
		return bookingDate;
	}
	public void setBookingDate(LocalDate bookingDate) {
		this.bookingDate = bookingDate;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public SeatAvailability getSeatAvailability() {
		return seatAvailability;
	}
	public void setSeatAvailability(SeatAvailability seatAvailability) {
		this.seatAvailability = seatAvailability;
	}
	public Integer getTotalRate() {
		return totalRate;
	}
	public void setTotalRate(Integer totalRate) {
		this.totalRate = totalRate;
	}
	
	

}
