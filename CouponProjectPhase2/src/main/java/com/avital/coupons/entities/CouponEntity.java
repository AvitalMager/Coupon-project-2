package com.avital.coupons.entities;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.avital.coupons.dto.CouponDto;
import com.avital.coupons.dto.CouponInfoDto;
import com.avital.coupons.enums.CouponCategory;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name= "coupons")
public class CouponEntity implements Serializable {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private long id;
	
	@Column(name = "name", unique = true, nullable = false)
	private String name;
	
	@Column(name = "price", nullable = false)
	private float price;
	
	@Column(name = "description", nullable = false)
	private String description;
	
	@Column(name = "start_date", nullable = false)
	private Date startDate;
	
	@Column(name = "end_date", nullable = false)
	private Date endDate;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "coupon_category", nullable = false)
	private CouponCategory couponCategory;
	
	@Column(name = "amount", nullable = false)
	private int amount;
	
	@Column(name = "image", nullable = true )
	private String image;
	
	@JsonIgnore
	@ManyToOne
	private CompanyEntity company;
	
	@JsonIgnore
	@OneToMany(mappedBy = "coupon", cascade = CascadeType.REMOVE)
	private List <PurchaseEntity> purchases;
	
	public CouponEntity() {
		
	}

	public CouponEntity(long id, String name, float price, String description, Date startDate, Date endDate,
			CouponCategory couponCategory, int amount, String image, CompanyEntity company, List<PurchaseEntity> purchases) {
		
		this.id = id;
		this.name = name;
		this.price = price;
		this.description = description;
		this.startDate = startDate;
		this.endDate = endDate;
		this.couponCategory = couponCategory;
		this.amount = amount;
		this.image = image;
		this.company = company;
		this.purchases = purchases;
	}
	
	public CouponEntity(CouponDto couponDto) {
		
		this.id = couponDto.getId();
		this.name = couponDto.getName();
		this.price = couponDto.getPrice();
		this.amount = couponDto.getAmount();
		this.description = couponDto.getDescription();
		this.startDate = couponDto.getStartDate();
		this.endDate = couponDto.getEndDate();
		this.couponCategory = couponDto.getCouponCategory();
		this.image = couponDto.getImage();
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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public CompanyEntity getCompany() {
		return company;
	}

	public void setCompany(CompanyEntity company) {
		this.company = company;
	}

	public List<PurchaseEntity> getPurchases() {
		return purchases;
	}

	public void setPurchases(List<PurchaseEntity> purchases) {
		this.purchases = purchases;
	}

	@Override
	public String toString() {
		return "Coupon [id=" + id + ", name=" + name + ", price=" + price + ", description=" + description
				+ ", startDate=" + startDate + ", endDate=" + endDate + ", couponCategory=" + couponCategory
				+ ", amount=" + amount + ", image=" + image + ", company=" + company + ", purchases=" + purchases + "]";
	}
}
