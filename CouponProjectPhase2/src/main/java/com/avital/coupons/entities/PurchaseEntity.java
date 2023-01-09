package com.avital.coupons.entities;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.avital.coupons.dto.PurchaseDto;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "purchases")
public class PurchaseEntity implements Serializable {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private long id;
	
	@Column(name = "amount", nullable = false)
	private int amount;
	
	@Column(name = "time_of_purchase", nullable = false)
	private Timestamp timeOfPurchase;
	
	@JsonIgnore
	@ManyToOne
	private UserEntity user;
	
	@JsonIgnore
	@ManyToOne
	private CouponEntity coupon;
	
	public PurchaseEntity() {
		
	}

	public PurchaseEntity(long id, int amount, Timestamp timeOfPurchase, UserEntity user, CouponEntity coupon) {
		
		this.id = id;
		this.amount = amount;
		this.timeOfPurchase = timeOfPurchase;
		this.user = user;
		this.coupon = coupon;
	}
	
	public PurchaseEntity(PurchaseDto purchaseDto) {
		
		this.id = purchaseDto.getId();
		this.amount = purchaseDto.getAmount();
		this.timeOfPurchase = purchaseDto.getTimeOfPurchase();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Timestamp getTimeOfPurchase() {
		return timeOfPurchase;
	}

	public void setTimeOfPurchase(Timestamp timeOfPurchase) {
		this.timeOfPurchase = timeOfPurchase;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public CouponEntity getCoupon() {
		return coupon;
	}

	public void setCoupon(CouponEntity coupon) {
		this.coupon = coupon;
	}

	@Override
	public String toString() {
		return "Purchase [id=" + id + ", amount=" + amount + ", timeOfPurchase=" + timeOfPurchase + ", user=" + user
				+ ", coupon=" + coupon + "]";
	}
}
