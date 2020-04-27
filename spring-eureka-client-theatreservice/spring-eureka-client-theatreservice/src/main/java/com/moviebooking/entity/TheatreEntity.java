package com.moviebooking.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="THEATRE")
public class TheatreEntity {
	@Id
	@Column(name="THEATRE_ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer theatreId;
	@Column(name="THEATRE_NAME")
	private String theatreName;
	@Column(name="THEATRE_LOCATION")
	private String theatreLocation;
	@Column(name="THEATRE_CAPACITY")
	private Integer theatreCapacity;
	@Column(name="THEATRE_PRICE")
	private Integer theatrePrice;
	
	
	
	public Integer getTheatreId() {
		return theatreId;
	}
	public void setTheatreId(Integer theatreId) {
		this.theatreId = theatreId;
	}
	public String getTheatreName() {
		return theatreName;
	}
	public void setTheatreName(String theatreName) {
		this.theatreName = theatreName;
	}
	public String getTheatreLocation() {
		return theatreLocation;
	}
	public void setTheatreLocation(String theatreLocation) {
		this.theatreLocation = theatreLocation;
	}
	public Integer getTheatreCapacity() {
		return theatreCapacity;
	}
	public void setTheatreCapacity(Integer theatreCapacity) {
		this.theatreCapacity = theatreCapacity;
	}
	public Integer getTheatrePrice() {
		return theatrePrice;
	}
	public void setTheatrePrice(Integer theatrePrice) {
		this.theatrePrice = theatrePrice;
	}
	
	
	
}
