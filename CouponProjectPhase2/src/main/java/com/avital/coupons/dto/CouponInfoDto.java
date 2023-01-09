package com.avital.coupons.dto;

import java.sql.Date;

import com.avital.coupons.entities.CouponEntity;
import com.avital.coupons.enums.CouponCategory;

public class CouponInfoDto {

	private String couponName;
	private float price;
	private String description;
	private Date endDate;
	private CouponCategory couponCategory;
	private String image;
	private String companyName;
	

	public CouponInfoDto(String couponName, float price, String description, Date endDate,
			CouponCategory couponCategory, String image, String companyName) {

		this.couponName = couponName;
		this.price = price;
		this.description = description;
		this.endDate = endDate;
		this.couponCategory = couponCategory;
		this.image = image;
		this.companyName = companyName;
	}

	public CouponInfoDto() {
	
	}
	
	public CouponInfoDto(CouponEntity couponEntity) {
		
		this.couponName = couponEntity.getName();
		this.price = couponEntity.getPrice();
		this.description = couponEntity.getDescription();
		this.endDate = couponEntity.getEndDate();
		this.couponCategory = couponEntity.getCouponCategory();
		this.image = couponEntity.getImage();
		this.companyName = couponEntity.getCompany().getCompanyName();
	}

	public String getCouponName() {
		return couponName;
	}

	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public CouponCategory getCouponCategory() {
		return couponCategory;
	}

	public void setCouponCategory(CouponCategory couponCategory) {
		this.couponCategory = couponCategory;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	@Override
	public String toString() {
		return "CouponInfoDto [couponName=" + couponName + ", price=" + price + ", description=" + description
				+ ", endDate=" + endDate + ", couponCategory=" + couponCategory + ", image=" + image + ", companyName="
				+ companyName + "]";
	}
}
