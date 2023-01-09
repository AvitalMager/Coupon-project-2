package com.avital.coupons.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.avital.coupons.dto.CompanyDto;
import com.avital.coupons.entities.CompanyEntity;

public interface ICompaniesDao extends CrudRepository <CompanyEntity, Long> {
	
	//checking is this company exists 
	public boolean existsByCompanyName(String companyName);
	
	//Get all companies
	@Query("select new com.avital.coupons.dto.CompanyDto(c) from CompanyEntity c")
	public List <CompanyDto> getAllCompanies();
}
