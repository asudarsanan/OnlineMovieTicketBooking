package com.moviebooking.entity;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="BOOKING")
public class BookingEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer bookingId;
	private Integer noOfTickets;
	private LocalDate bookingDate;
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="USERID")
	private UserEntity userEntity;
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="SEATID")
	private SeatAvailabilityEntity seatAvailabilityEntity;
	@Column(name="TOTAL_BOOKING_RATE")
	private Integer totalRate;
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="MOVIEID")
	private MovieEntity movieEntity;
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="THEATREID")
	private TheatreEntity theatreEntity;
	private String bookingTime;
	
	
	public String getBookingTime() {
		return bookingTime;
	}
	public void setBookingTime(String bookingTime) {
		this.bookingTime = bookingTime;
	}
	public MovieEntity getMovieEntity() {
		return movieEntity;
	}
	public void setMovieEntity(MovieEntity movieEntity) {
		this.movieEntity = movieEntity;
	}
	public TheatreEntity getTheatreEntity() {
		return theatreEntity;
	}
	public void setTheatreEntity(TheatreEntity theatreEntity) {
		this.theatreEntity = theatreEntity;
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
	public UserEntity getUserEntity() {
		return userEntity;
	}
	public void setUserEntity(UserEntity userEntity) {
		this.userEntity = userEntity;
	}
	public SeatAvailabilityEntity getSeatAvailabilityEntity() {
		return seatAvailabilityEntity;
	}
	public void setSeatAvailabilityEntity(SeatAvailabilityEntity seatAvailabilityEntity) {
		this.seatAvailabilityEntity = seatAvailabilityEntity;
	}
	public Integer getTotalRate() {
		return totalRate;
	}
	public void setTotalRate(Integer totalRate) {
		this.totalRate = totalRate;
	}
	
	

}
