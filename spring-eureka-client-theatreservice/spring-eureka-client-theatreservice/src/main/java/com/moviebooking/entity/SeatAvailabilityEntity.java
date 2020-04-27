package com.moviebooking.entity;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="SEATAVAILABILITY")
public class SeatAvailabilityEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)	
	private Integer seatAvailId;
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="SHOWID")
	private ShowEntity showEntity;
	private LocalDate userDate;
	private Integer seatingCapacity;
	public Integer getSeatAvailId() {
		return seatAvailId;
	}
	public void setSeatAvailId(Integer seatAvailId) {
		this.seatAvailId = seatAvailId;
	}
	public ShowEntity getShowEntity() {
		return showEntity;
	}
	public void setShowEntity(ShowEntity showEntity) {
		this.showEntity = showEntity;
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
