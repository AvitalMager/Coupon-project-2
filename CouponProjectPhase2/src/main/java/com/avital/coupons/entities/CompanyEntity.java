package com.avital.coupons.entities;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.avital.coupons.dto.CompanyDto;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Companies")
public class CompanyEntity implements Serializable {
	
	@Id
	@GeneratedValue
	@Column(name= "id")
	private long id;
	
	@Column(name = "company_name", unique= true, nullable = false)
	private String companyName;
	
	@Column(name= "phone_number", nullable = false)
	private String phoneNumber;
	
	@Column(name= "address", nullable = false)
	private String address;
	
	@JsonIgnore
	@OneToMany(mappedBy = "company", cascade = CascadeType.REMOVE)
	private List <UserEntity> users;
	
	@JsonIgnore
	@OneToMany(mappedBy = "company",cascade = CascadeType.REMOVE)
	private List <CouponEntity> coupons;
	
	public CompanyEntity(long id, String companyName, String phoneNumber, String address, List<UserEntity> users,
			List<CouponEntity> coupons) {
		this.id = id;
		this.companyName = companyName;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.users = users;
		this.coupons = coupons;
	}

	public CompanyEntity(String companyName, String phoneNumber, String address, List<UserEntity> users,
			List<CouponEntity> coupons) {
		super();
		this.companyName = companyName;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.users = users;
		this.coupons = coupons;
	}
	
	public CompanyEntity(CompanyDto companyDto) {
		
		this.id = companyDto.getId();
		this.companyName = companyDto.getCompanyName();
		this.phoneNumber = companyDto.getPhoneNumber();
		this.address = companyDto.getAddress();
	}

	public CompanyEntity() {
		
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

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<UserEntity> getUsers() {
		return users;
	}

	public void setUsers(List<UserEntity> users) {
		this.users = users;
	}

	public List<CouponEntity> getCoupons() {
		return coupons;
	}

	public void setCoupons(List<CouponEntity> coupons) {
		this.coupons = coupons;
	}

	@Override
	public String toString() {
		return "Company [id=" + id + ", companyName=" + companyName + ", phoneNumber=" + phoneNumber + ", address="
				+ address + ", users=" + users + ", coupons=" + coupons + "]";
	}
}
