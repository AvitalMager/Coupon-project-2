package com.avital.coupons.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.avital.coupons.data.UserLoginData;
import com.avital.coupons.dto.UserDto;
import com.avital.coupons.dto.UserInfoDto;
import com.avital.coupons.entities.UserEntity;


public interface IUsersDao extends CrudRepository <UserEntity, Long>{

	//Checking is this user name exists 
	public boolean existsByUserName(String userName);
	
	//login
	@Query("select new com.avital.coupons.data.UserLoginData(u.id, u.userType, u.company.id) from UserEntity u where userName= :userName and password= :password")
	public UserLoginData login(@Param("userName") String userName, @Param("password") String password);
	
	//get all users
	@Query("select new com.avital.coupons.dto.UserInfoDto(u) from UserEntity u")
	public List<UserInfoDto> getAllUsers();
}
