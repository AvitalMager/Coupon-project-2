package com.avital.coupons.logic;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.avital.coupons.dao.ICompaniesDao;
import com.avital.coupons.dto.CompanyDto;
import com.avital.coupons.entities.CompanyEntity;
import com.avital.coupons.enums.ErrorType;
import com.avital.coupons.exceptions.ApplicationException;

@Controller
public class CompaniesController {

	@Autowired
	private ICompaniesDao companiesDao;

	public CompaniesController() {
	}

	public long createCompany(CompanyDto companyDto) throws ApplicationException {

		try {
			// validating before creating company
			validateCreateOrUpdateCompany(companyDto);
			
			// checking if company exists
			validateIsCompanyExists(companyDto);

			// Creating company entity
			CompanyEntity companyEntity = new CompanyEntity(companyDto);

			// creating the company
			this.companiesDao.save(companyEntity);

			return companyEntity.getId();

		} catch (ApplicationException e) {
			// If there was an exception in "try" block above, it is caught here and
			// notifies the level above.
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR, companyDto.toString());
		}

	}

	public void updateCompany(CompanyDto companyDto) throws ApplicationException {

		try {
			// validating before company update
			validateCreateOrUpdateCompany(companyDto);

			// Creating company entity
			CompanyEntity companyEntity = new CompanyEntity(companyDto);

			// Execute update company
			this.companiesDao.save(companyEntity);

		} catch (ApplicationException e) {
			// If there was an exception in "try" block above, it is caught here and
			// notifies the level above.
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR,
					"failed, can not update company details" + companyDto.toString());
		}

	}

	public void deleteCompany(long id) throws ApplicationException {

		try {
			// Deleting company
			this.companiesDao.deleteById(id);

		} catch (Exception e) {
			// If there was an exception in "try" block above, it is caught here and
			// notifies the level above.
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR,
					"failed, company has been not deleted. company id: " + id);
		}

	}

	private void validateCreateOrUpdateCompany(CompanyDto companyDto) throws ApplicationException {

		// Validating company name
		if (companyDto.getCompanyName() == null) {
			throw new ApplicationException(ErrorType.MUST_ENTER_NAME, companyDto.getCompanyName());
		}

		// Validating company name
		if (companyDto.getCompanyName().isEmpty()) {
			throw new ApplicationException(ErrorType.MUST_ENTER_NAME, companyDto.getCompanyName());
		}

		// Validating company name
		if (companyDto.getCompanyName().length() < 2) {
			throw new ApplicationException(ErrorType.NAME_TOO_SHORT, companyDto.getCompanyName());
		}

		// Validating company address
		if (companyDto.getAddress() == null) {
			throw new ApplicationException(ErrorType.INVALID_ADDRESS, companyDto.getAddress());
		}

		// Validating company phone number
		if (companyDto.getPhoneNumber() == null) {
			throw new ApplicationException(ErrorType.INVALID_PHONE_NUMBER, companyDto.getPhoneNumber());
		}

	}

	private void validateIsCompanyExists(CompanyDto companyDto) throws ApplicationException {

		// Validating if company name exists
		if (this.companiesDao.existsByCompanyName(companyDto.getCompanyName())) {
			throw new ApplicationException(ErrorType.NAME_ALREADY_EXISTS, companyDto.getCompanyName());
		}
	}

	public CompanyDto getCompany(long id) throws ApplicationException {

		try {
			// Execute get company
			CompanyEntity companyEntity = this.companiesDao.findById(id).get();

			// Creating company dto
			CompanyDto companyDto = new CompanyDto(companyEntity);

			return companyDto;

		} catch (Exception e) {
			// If there was an exception in "try" block above, it is caught here and
			// notifies the level above.
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR,
					"failed, can not get companies details. company id: " + id);
		}
	}

	public List<CompanyDto> getAllCompanies() throws ApplicationException {

		try {
			// Executing getAllCompanies
			List<CompanyDto> companies = this.companiesDao.getAllCompanies();

			return companies;

		} catch (Exception e) {
			// If there was an exception in "try" block above, it is caught here and
			// notifies the level above.
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR, "failed, can not get allCompanies details");
		}
	}
}