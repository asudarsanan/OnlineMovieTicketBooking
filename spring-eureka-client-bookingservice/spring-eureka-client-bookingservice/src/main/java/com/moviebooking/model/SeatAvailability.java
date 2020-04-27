package com.moviebooking.model;

import java.time.LocalDate;

public class SeatAvailability {
	private Integer seatAvailId;
	private Show show;
	private LocalDate userDate;
	private Integer seatingCapacity;
	
	public Integer getSeatAvailId() {
		return seatAvailId;
	}
	public void setSeatAvailId(Integer seatAvailId) {
		this.seatAvailId = seatAvailId;
	}
	public Show getShow() {
		return show;
	}
	public void setShow(Show show) {
		this.show = show;
	}
	public LocalDate getUserDate() {
		return userDate;
	}
	public void setUserDate(LocalDate userDate) {
		this.userDate = userDate;
	}
	public Integer getSeatingCapacity() {
		return seatingCapacity;
	}
	public void setSeatingCapacity(Integer seatingCapacity) {
		this.seatingCapacity = seatingCapacity;
	}
	
	

}
