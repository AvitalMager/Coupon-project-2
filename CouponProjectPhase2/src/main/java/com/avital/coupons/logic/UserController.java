package com.avital.coupons.logic;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.avital.coupons.dao.IUsersDao;
import com.avital.coupons.data.SuccessfulLoginData;
import com.avital.coupons.data.UserLoginData;
import com.avital.coupons.dto.UserDto;
import com.avital.coupons.dto.UserInfoDto;
import com.avital.coupons.dto.UserLoginDetails;
import com.avital.coupons.entities.CompanyEntity;
import com.avital.coupons.entities.UserEntity;
import com.avital.coupons.enums.ErrorType;
import com.avital.coupons.enums.UserType;
import com.avital.coupons.exceptions.ApplicationException;

@Controller
public class UserController {

	@Autowired
	private CompaniesController companiesController;

	@Autowired
	private CacheController cacheController;

	@Autowired
	private IUsersDao userDao;

	private static final String ENCRYPTION_TOKEN_SALT = "SDDFDFGHLKAJLSKJDEI";

	public UserController() {
	}

	public long createUser(UserDto userDto) throws ApplicationException {

		try {
			// Checking if user name exists
			isUserNameExists(userDto);

			// Validating user details before creation
			validateCreateOrUpdateUser(userDto);

			// Setting hash code for the password
			userDto.setPassword(String.valueOf(userDto.getPassword().hashCode()));

			// Creating user entity
			UserEntity userEntity = new UserEntity(userDto);

			// Checking if company id is not null, if so setting company id
			if (userDto.getCompanyId() != null) {

				CompanyEntity companyEntity = new CompanyEntity(companiesController.getCompany(userDto.getCompanyId()));

				userEntity.setCompany(companyEntity);
			}

			// Execute create user
			this.userDao.save(userEntity);

			return userEntity.getId();

		} catch (ApplicationException e) {

			// If there was an exception in "try" block above, it is caught here and
			// notifies the level above.
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR, userDto.toString());
		}
	}

	public void updateUser(UserDto userDto) throws ApplicationException {

		try {
			// Validating user details before update
			validateCreateOrUpdateUser(userDto);

			// Creating user entity
			UserEntity userEntity = new UserEntity(userDto);

			// Setting hash code for the password
			userEntity.setPassword(String.valueOf(userEntity.getPassword().hashCode()));

			// Checking if company id is not null, if so setting company id
			if (userDto.getCompanyId() != null) {

				CompanyEntity companyEntity = new CompanyEntity(companiesController.getCompany(userDto.getCompanyId()));

				userEntity.setCompany(companyEntity);
			}
			// Execute update user
			this.userDao.save(userEntity);

		} catch (ApplicationException e) {
			// If there was an exception in "try" block above, it is caught here and
			// notifies the level above.
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR,
					"failed, can not update user details" + userDto.toString());
		}
	}

	private void validateCreateOrUpdateUser(UserDto user) throws ApplicationException {

		// Checking if user name is not null
		if (user.getUserName() == null) {
			throw new ApplicationException(ErrorType.MUST_ENTER_NAME, user.toString());
		}

		// Checking id user name is not empty
		if (user.getUserName().isEmpty()) {
			throw new ApplicationException(ErrorType.MUST_ENTER_NAME, user.toString());
		}

		// Checking user name length
		if (user.getUserName().length() < 2) {
			throw new ApplicationException(ErrorType.NAME_TOO_SHORT, user.toString());
		}

		// Making sure that this user is actually company user type and he has company
		// id
		if (user.getUserType().equals(UserType.COMPANY) == false && user.getCompanyId() != null) {
			throw new ApplicationException(ErrorType.GENERAL_ERROR, user.toString());
		}
	}

	public void isUserNameExists(UserDto user) throws ApplicationException {

		// Checking if user name already exists
		if (this.userDao.existsByUserName(user.getUserName())) {
			throw new ApplicationException(ErrorType.NAME_ALREADY_EXISTS, user.toString());
		}
	}

	public List<UserInfoDto> getAllUsers() throws ApplicationException {

		try {
			// Execute get all users
			List<UserInfoDto> users = userDao.getAllUsers();

			return users;

		} catch (Exception e) {
			// If there was an exception in "try" block above, it is caught here and
			// notifies the level above.
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR, "failed, can not get allUssers details");
		}
	}

	public UserInfoDto getUser(long id) throws ApplicationException {

		try {
			// Execute get user
			UserEntity userEntity = this.userDao.findById(id).get();

			// Creating user dto
			UserInfoDto userDto = new UserInfoDto(userEntity);

			return userDto;

		} catch (Exception e) {
			// If there was an exception in "try" block above, it is caught here and
			// notifies the level above.
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR,
					"failed, can not get user details. user id: " + id);
		}
	}

	public void deleteUser(long id) throws ApplicationException {
		try {
			// deleting user
			this.userDao.deleteById(id);

		} catch (Exception e) {
			// If there was an exception in "try" block above, it is caught here and
			// notifies the level above.
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR, "failed, can not delete user. id: " + id);
		}
	}

	public SuccessfulLoginData login(UserLoginDetails userLoginDetails) throws ApplicationException {

		// Setting hash code
		userLoginDetails.setPassword(String.valueOf(userLoginDetails.getPassword().hashCode()));

		// Receiving user login data
		UserLoginData userLoginData = this.userDao.login(userLoginDetails.getUsername(),
				userLoginDetails.getPassword());

		// If there is no login data for this user it means that the user did not logged
		// in
		if (userLoginData == null) {
			throw new ApplicationException(ErrorType.LOGIN_FAILED, "Username or password are incorrect");
		}

		// Creating token
		int token = generateToken(userLoginDetails);

		// Saving login data in cache controller
		cacheController.put(String.valueOf(token), userLoginData);

		return new SuccessfulLoginData(token, userLoginData.getUserType());
	}

	private int generateToken(UserLoginDetails userLoginDetails) {

		// Creating token
		String text = userLoginDetails.getUsername() + Calendar.getInstance().getTime().toString()
				+ ENCRYPTION_TOKEN_SALT;

		return text.hashCode();
	}

	public UserEntity getUserEntity(long id) throws ApplicationException {

		try {
			// Receiving user entity
			UserEntity user = this.userDao.findById(id).get();
			
			return user;

		} catch (Exception e) {
			// If there was an exception in "try" block above, it is caught here and
			// notifies the level above.
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR,
					"failed, can not get user entity. id:" + id);
		}
	}
}
