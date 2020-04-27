package com.moviebooking.model;

import java.time.LocalDate;

public class Report { 
	private Integer reportId;
	private Show show;
	private Integer noOfTicketsBookedOrCancelled;
	private LocalDate userDate;
	private String status;
	
	
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public LocalDate getUserDate() {
		return userDate;
	}
	public void setUserDate(LocalDate userDate) {
		this.userDate = userDate;
	}
	public Integer getReportId() {
		return reportId;
	}
	public void setReportId(Integer reportId) {
		this.reportId = reportId;
	}
	public Show getShow() {
		return show;
	}
	public void setShow(Show show) {
		this.show = show;
	}
	public Integer getNoOfTicketsBookedOrCancelled() {
		return noOfTicketsBookedOrCancelled;
	}
	public void setNoOfTicketsBookedOrCancelled(Integer noOfTicketsBookedOrCancelled) {
		this.noOfTicketsBookedOrCancelled = noOfTicketsBookedOrCancelled;
	}
	
	

}
