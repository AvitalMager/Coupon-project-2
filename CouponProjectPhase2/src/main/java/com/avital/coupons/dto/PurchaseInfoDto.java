package com.avital.coupons.dto;

import java.sql.Timestamp;

import com.avital.coupons.entities.PurchaseEntity;

public class PurchaseInfoDto {

	private String couponName;
	private int amount;
	private Timestamp timeOfPurchase;
	
	public PurchaseInfoDto(String couponName, int amount, Timestamp timeOfPurchase) {
	
		this.couponName = couponName;
		this.amount = amount;
		this.timeOfPurchase = timeOfPurchase;
	}

	public PurchaseInfoDto() {
	
	}
	
	public PurchaseInfoDto(PurchaseEntity purchaseEntity) {
		
		this.couponName = purchaseEntity.getCoupon().getName();
		this.amount = purchaseEntity.getAmount();
		this.timeOfPurchase = purchaseEntity.getTimeOfPurchase();
	}

	public String getCouponName() {
		return couponName;
	}

	public void setCouponName(String couponName) {
		this.couponName = couponName;
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

	@Override
	public String toString() {
		return "PurchaseInfoDto [couponName=" + couponName + ", amount=" + amount + ", timeOfPurchase=" + timeOfPurchase
				+ "]";
	}
}
