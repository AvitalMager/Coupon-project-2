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

import com.avital.coupons.data.UserLoginData;
import com.avital.coupons.dto.CompanyDto;
import com.avital.coupons.exceptions.ApplicationException;
import com.avital.coupons.logic.CompaniesController;

@RestController
@RequestMapping("/company")
public class CompanyApi {

	@Autowired
	CompaniesController companiesController;

	@PostMapping
	public Long createCompany(@RequestBody CompanyDto companyDto) throws ApplicationException {

		// Execute create company
		long companyId = this.companiesController.createCompany(companyDto);
		
		return companyId;
	}

	@PutMapping
	public void updateCompany(@RequestBody CompanyDto companyDto, HttpServletRequest request)
			throws ApplicationException {

		// Receiving user login data
		UserLoginData userLoginData = (UserLoginData) request.getAttribute("userLoginData");

		// Receiving company id from user login data
		companyDto.setId(userLoginData.getCompanyId());

		// Execute update user
		this.companiesController.updateCompany(companyDto);

	}

	@DeleteMapping("/{companyId}")
	public void deleteCompany(@PathVariable("companyId") Long id) throws ApplicationException {

		// Execute delete company
		this.companiesController.deleteCompany(id);
	}

	@GetMapping("/{companyId}")
	public CompanyDto getCompany(@PathVariable("companyId") long id) throws ApplicationException {

		// Execute delete user
		return this.companiesController.getCompany(id);
	}

	@GetMapping
	public List<CompanyDto> getAllCompanies() throws ApplicationException {

		// Execute get all companies
		return (List<CompanyDto>) this.companiesController.getAllCompanies();
	}
}
