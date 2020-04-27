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
@Table(name="SHOWS")
public class ShowEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer showId;
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="MOVIEID")
	private MovieEntity movieEntity;
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="THEATREID")
	private TheatreEntity theatreEntity;
	private String showTime;
	private LocalDate startDate;
	private LocalDate endDate;
	
	
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
	public Integer getShowId() {
		return showId;
	}
	public void setShowId(Integer showId) {
		this.showId = showId;
	}
	
	public String getShowTime() {
		return showTime;
	}
	public void setShowTime(String showTime) {
		this.showTime = showTime;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	
	

}
