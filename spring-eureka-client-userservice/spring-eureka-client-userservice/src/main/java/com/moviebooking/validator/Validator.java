package com.moviebooking.validator;

import com.moviebooking.model.User;

public class Validator {
	
	public static void validateUser(User user) throws Exception{
		if(!validateName(user.getUserName())) {
			throw new Exception("UserValidator.INVALID_NAME");
		}
		if(!validateEmailId(user.getUserEmailid())) {
			throw new Exception("UserValidator.INVALID_EMAILID");
		}
		if(!validatePhoneNumber(user.getUserPhoneNumber())) {
			throw new Exception("UserValidator.INVALID_PHONE_NUMBER");
		}
	}
	
	
	
	public static Boolean validateName(String name){
		Boolean flag = false;
		if(!name.matches("[ ]*") && name.matches("([A-Za-z])+(\\s[A-Za-z]+)*"))
			flag=true;
		return flag;
	}
	
	public static Boolean validateEmailId(String emailId){
		Boolean flag = false;
		if(emailId.matches("[a-zA-Z0-9._]+@[a-zA-Z]{2,}\\.[a-zA-Z][a-zA-Z.]+"))
			flag = true;
		return flag;
	}
	
	public static Boolean validatePhoneNumber(String phoneNumber){
		Boolean flag = false;
		if(phoneNumber.matches("[0-9]+") && phoneNumber.length()==10)
			flag=true;
		return flag;
	}




}
