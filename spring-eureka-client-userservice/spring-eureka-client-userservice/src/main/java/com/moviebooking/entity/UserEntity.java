package com.moviebooking.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="USER")
public class UserEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer userId;
	private String userName;
	private String userEmailid;
	private String userPassword;
	private String userPhoneNumber;
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="ROLE_NAME")
	private RoleEntity roleEntity;
	
	
	
	
	public RoleEntity getRoleEntity() {
		return roleEntity;
	}
	public void setRoleEntity(RoleEntity roleEntity) {
		this.roleEntity = roleEntity;
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
