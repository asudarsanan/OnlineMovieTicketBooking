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
@Table(name="REPORT")
public class ReportEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer reportId;
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="SHOWID")
	private ShowEntity showEntity;
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
	public ShowEntity getShowEntity() {
		return showEntity;
	}
	public void setShowEntity(ShowEntity showEntity) {
		this.showEntity = showEntity;
	}
	public Integer getNoOfTicketsBookedOrCancelled() {
		return noOfTicketsBookedOrCancelled;
	}
	public void setNoOfTicketsBookedOrCancelled(Integer noOfTicketsBookedOrCancelled) {
		this.noOfTicketsBookedOrCancelled = noOfTicketsBookedOrCancelled;
	}
	
	

}
