package com.moviebooking.api;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;


import com.moviebooking.model.User;
import com.moviebooking.service.UserService;

@RestController
@RequestMapping(value="/user")
public class UserAPI {
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private UserService userService;
	
	@PostMapping(value="/loginUser")
	public ResponseEntity<User> loginUser(@RequestBody User user){
		try {
			User userFromDB=userService.loginUser(user.getUserEmailid(), user.getUserPassword(), user.getRole());
			return new ResponseEntity<User>(userFromDB,HttpStatus.OK);
		}
		catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, environment.getProperty(e.getMessage()));
		}

	}
		
	
	
	
	@PostMapping(value="/registerNewUser")
	public ResponseEntity<String> registerNewUser(@RequestBody User user) throws Exception{
		String str=null;
		try {
			Integer id=userService.registerNewUser(user);
			str=environment.getProperty("UserAPI.USER_REGISTRATION_SUCCESS")+id;
			return new ResponseEntity<String>(str,HttpStatus.OK);
		}
		catch (Exception e){
			throw new ResponseStatusException(HttpStatus.CONFLICT, environment.getProperty(e.getMessage()));
		}

	}
	
	@PostMapping(value="/updateProfile")
	public ResponseEntity<String> updateProfile(@RequestBody User user) throws Exception{
		String str=null;
		try {
			Integer id=userService.updateProfile(user);
			str=environment.getProperty("UserAPI.USER_PROFILE_UPDATE_SUCCESS")+id;
			return new ResponseEntity<String>(str,HttpStatus.OK);
		}
		catch (Exception e){
			throw new ResponseStatusException(HttpStatus.CONFLICT, environment.getProperty(e.getMessage()));
		}
	}
	


}
