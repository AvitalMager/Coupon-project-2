package com.avital.coupons.dto;

import com.avital.coupons.entities.UserEntity;
import com.avital.coupons.enums.UserType;

public class UserDto {

	private long id;
	private String userName;
	private String password;
	private String firstName;
	private String lastName;
	private UserType userType;
	private Long companyId;

	public UserDto() {

	}

	public UserDto(String userName, String password, String firstName, String lastName, UserType userType,
			Long companyId) {

		this.userName = userName;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userType = userType;
		this.companyId = companyId;
	}

	public UserDto(long id, String userName, String firstName, String lastName, UserType userType) {

		this.id = id;
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userType = userType;
	}

	
	public UserDto(String userName, String password, String firstName, String lastName, UserType userType) {

		this.password = password;
		this.userName = userName;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userType = userType;
	}
	

	public UserDto(long id, String userName, String password, String firstName, String lastName, UserType userType,
			Long companyId) {
		
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userType = userType;
		this.companyId = companyId;
	}

	public UserDto(UserEntity userEntity) {

		this.id = userEntity.getId();
		this.userName = userEntity.getUserName();
		this.firstName = userEntity.getFirstName();
		this.lastName = userEntity.getLastName();
		this.userType = userEntity.getUserType();
	
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	@Override
	public String toString() {
		return "UserDto [id=" + id + ", userName=" + userName + ", password=" + password + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", userType=" + userType + ", companyId=" + companyId + "]";
	}
}
