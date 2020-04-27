package com.moviebooking.model;


public class User {
	private Integer userId;
	private String userName;
	private String userEmailid;
	private String userPassword;
	private String userPhoneNumber;	
	private Role role;
	
	
	
	
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public String getUserPhoneNumber() {
		return userPhoneNumber;
	}
	public void setUserPhoneNumber(String userPhoneNumber) {
		this.userPhoneNumber = userPhoneNumber;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserEmailid() {
		return userEmailid;
	}
	public void setUserEmailid(String userEmailid) {
		this.userEmailid = userEmailid;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	
	
}
