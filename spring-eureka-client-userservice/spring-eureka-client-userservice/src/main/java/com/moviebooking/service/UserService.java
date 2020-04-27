package com.moviebooking.service;


import com.moviebooking.model.Role;

import com.moviebooking.model.User;

public interface UserService {

	public Integer registerNewUser(User user) throws Exception;

	public User loginUser(String emailId, String password, Role role) throws Exception;

	public Integer updateProfile(User user) throws Exception;
	

}
