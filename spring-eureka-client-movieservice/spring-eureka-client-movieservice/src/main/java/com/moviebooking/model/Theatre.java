package com.moviebooking.model;

import java.util.List;

public class Theatre {
	private Integer theatreId;
	private String theatreName;
	private String theatreLocation;
	private Integer theatreCapacity;
	private Integer theatrePrice;
	private List<Show> showList;
	
	
	public List<Show> getShowList() {
		return showList;
	}
	public void setShowList(List<Show> showList) {
		this.showList = showList;
	}
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
