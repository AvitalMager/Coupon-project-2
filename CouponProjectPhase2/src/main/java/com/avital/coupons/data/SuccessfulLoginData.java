package com.avital.coupons.data;

import com.avital.coupons.enums.UserType;

public class SuccessfulLoginData {

	private int token;
	private UserType userType;
	
	
	public SuccessfulLoginData(int token, UserType userType) {
		this.token = token;
		this.userType = userType;
	}
	
	public SuccessfulLoginData() {
		
	}

	public int getToken() {
		return token;
	}

	public void setToken(int token) {
		this.token = token;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	
	
}
