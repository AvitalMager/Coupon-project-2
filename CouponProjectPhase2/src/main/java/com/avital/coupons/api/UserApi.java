package com.avital.coupons.api;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.avital.coupons.data.SuccessfulLoginData;
import com.avital.coupons.data.UserLoginData;
import com.avital.coupons.dto.UserDto;
import com.avital.coupons.dto.UserInfoDto;
import com.avital.coupons.dto.UserLoginDetails;
import com.avital.coupons.enums.UserType;
import com.avital.coupons.exceptions.ApplicationException;

import com.avital.coupons.logic.UserController;

@RestController
@RequestMapping("/user")
public class UserApi {

	@Autowired
	UserController userController;

	@PostMapping("/login")
	public SuccessfulLoginData login(@RequestBody UserLoginDetails userLoginDetails) throws ApplicationException {

		// Execute login
		SuccessfulLoginData data = this.userController.login(userLoginDetails);
		
		return data;
	}

	@PostMapping
	public Long createUser(@RequestBody UserDto userDto) throws ApplicationException {

		// Execute create user
		long userId = this.userController.createUser(userDto);

		return userId;
	}

	@PutMapping
	public void updateUser(@RequestBody UserDto userDto, HttpServletRequest request) throws ApplicationException {

		// Receiving user login data
		UserLoginData userLoginData = (UserLoginData) request.getAttribute("userLoginData");

		//Setting user id
		userDto.setId(userLoginData.getId());

		// Execute update user
		this.userController.updateUser(userDto);
	}

	@DeleteMapping("/{userId}")
	public void deleteUser(@PathVariable("userId") long id) throws ApplicationException {

		// Execute delete user
		this.userController.deleteUser(id);
	}

	@GetMapping("/{getUserById}")
	public UserInfoDto getUser(HttpServletRequest request) throws ApplicationException {

		// Receiving user login data
		UserLoginData userLoginData = (UserLoginData) request.getAttribute("userLoginData");

		// Execute get user
		UserInfoDto userDto = this.userController.getUser(userLoginData.getId());

		return userDto;
	}

	@GetMapping
	public List<UserInfoDto> getAllUsers() throws ApplicationException {

		// Execute get all users
		List<UserInfoDto> users = this.userController.getAllUsers();
		return users;
	}
}
