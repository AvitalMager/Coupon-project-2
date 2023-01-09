package com.avital.coupons.dto;

import com.avital.coupons.entities.UserEntity;
import com.avital.coupons.enums.UserType;

public class UserInfoDto {

	private String userName;
	private String firstName;
	private String lastName;
	private UserType userType;
	private String companyName;

	public UserInfoDto(String userName, String firstName, String lastName, UserType userType, String companyName) {
		super();
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userType = userType;
		this.companyName = companyName;
	}

	public UserInfoDto() {
		
	}
	
	public UserInfoDto(UserEntity userEntity) {
		
		this.userName = userEntity.getUserName();
		this.firstName = userEntity.getFirstName();
		this.lastName = userEntity.getLastName();
		this.userType = userEntity.getUserType();
		if (userEntity.getCompany() == null) {
			this.companyName = "";
		}else {
			this.companyName = userEntity.getCompany().getCompanyName();
		}
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
}
