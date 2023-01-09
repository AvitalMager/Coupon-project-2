package com.avital.coupons.dto;

import com.avital.coupons.entities.CompanyEntity;

public class CompanyDto {

	private long id;
	private String companyName;
	private String address;
	private String phoneNumber;
	
	public CompanyDto() { 
		
	}

	public CompanyDto(long id, String name, String address, String phoneNumber) {
		
		this(name, address, phoneNumber);
		this.id=id;
	}
	
	public CompanyDto(String name, String address, String phoneNumber) {
		
		this.companyName = name;
		this.address = address;
		this.phoneNumber = phoneNumber;
	}
	
	public CompanyDto(CompanyEntity companyEntity) {
		
		this.id = companyEntity.getId();
		this.companyName = companyEntity.getCompanyName();
		this.address = companyEntity.getAddress();
		this.phoneNumber = companyEntity.getPhoneNumber();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Override
	public String toString() {
		return "CompanyDto [id=" + id + ", companyName=" + companyName + ", address=" + address + ", phoneNumber="
				+ phoneNumber + "]";
	}
}
