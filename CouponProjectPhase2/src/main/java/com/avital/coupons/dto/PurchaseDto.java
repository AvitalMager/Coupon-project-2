package com.avital.coupons.dto;

import java.sql.Timestamp;

import com.avital.coupons.entities.PurchaseEntity;

public class PurchaseDto {

	private long id;
	private int amount;
	private Timestamp timeOfPurchase;
	private long userId;
	private long couponId;
	
	PurchaseDto() {
		
	}

	public PurchaseDto(long id, int amount, Timestamp timeOfPurchase, long userId, long couponId) {

		this.id = id;
		this.amount = amount;
		this.timeOfPurchase = timeOfPurchase;
		this.userId = userId;
		this.couponId = couponId;
	}

	public PurchaseDto(long id, int amount, Timestamp timeOfPurchase) {
		
		this.id = id;
		this.amount = amount;
		this.timeOfPurchase = timeOfPurchase;
	}
	
	public PurchaseDto(PurchaseEntity purchaseEntity) {
		
		this.id = purchaseEntity.getId();
		this.amount = purchaseEntity.getAmount();
		this.timeOfPurchase = purchaseEntity.getTimeOfPurchase();
		this.couponId = purchaseEntity.getCoupon().getId();
		this.userId = purchaseEntity.getUser().getId();
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

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getCouponId() {
		return couponId;
	}

	public void setCouponId(long couponId) {
		this.couponId = couponId;
	}

	@Override
	public String toString() {
		return "PurchaseDto [id=" + id + ", amount=" + amount + ", timeOfPurchase=" + timeOfPurchase + ", userId="
				+ userId + ", couponId=" + couponId + "]";
	}
}
