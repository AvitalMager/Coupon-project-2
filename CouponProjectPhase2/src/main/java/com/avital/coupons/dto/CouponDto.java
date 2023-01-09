package com.avital.coupons.dto;

import java.sql.Date;

import com.avital.coupons.entities.CouponEntity;
import com.avital.coupons.enums.CouponCategory;


public class CouponDto {

	private long id;
	private String name;
	private float price;
	private String description;
	private Date startDate;
	private Date endDate;
	private CouponCategory couponCategory;
	private int amount;
	private String image;
	private Long companyId;

	public CouponDto() {

	}

	public CouponDto(long id, String name, float price, String description, Date startDate, Date endDate,
			CouponCategory couponCategory, int amount, String image) {

		this.id = id;
		this.name = name;
		this.price = price;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.couponCategory = couponCategory;
		this.amount = amount;
		this.image = image;
	}

	public CouponDto(String name, float price, String description, Date startDate, Date endDate,
			CouponCategory couponCategory, int amount, String image, Long companyId) {

		this.name = name;
		this.price = price;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.couponCategory = couponCategory;
		this.amount = amount;
		this.image = image;
		this.companyId = companyId;
	}

	public CouponDto(long id, String name, float price, String description, Date startDate, Date endDate,
			CouponCategory couponCategory, int amount, String image, Long companyId) {

		this(name, price, description, startDate, endDate, couponCategory, amount, image, companyId);
		this.id = id;

	}

	public CouponDto(CouponEntity couponEntity) {

		this.id = couponEntity.getId();
		this.name = couponEntity.getName();
		this.price = couponEntity.getPrice();
		this.description = couponEntity.getDescription();
		this.startDate = couponEntity.getStartDate();
		this.endDate = couponEntity.getEndDate();
		this.couponCategory = couponEntity.getCouponCategory();
		this.amount = couponEntity.getAmount();
		this.image = couponEntity.getImage();
		this.companyId = couponEntity.getCompany().getId();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
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

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "CouponDto [id=" + id + ", name=" + name + ", price=" + price + ", description=" + description
				+ ", startDate=" + startDate + ", endDate=" + endDate + ", couponCategory=" + couponCategory
				+ ", amount=" + amount + ", image=" + image + ", companyId=" + companyId + "]";
	}
}
