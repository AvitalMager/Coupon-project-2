package com.avital.coupons.couponExpiration;


import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.avital.coupons.exceptions.ApplicationException;
import com.avital.coupons.logic.CouponsController;
import com.avital.coupons.logic.PurchasesController;


@EnableScheduling
@Component
public class InitClass {

	@Autowired
	private CouponsController couponsController;
	
	@Autowired
	private PurchasesController purchasesController;
	
	//Setting time
	@PostConstruct
	@Scheduled(cron="0 0 0 * * ?")
	public void init() throws ApplicationException {
		
		//Execute delete old purchases
		this.purchasesController.deleteExpiredCouponPurchases();
		
		//Execute delete old coupons
		this.couponsController.deleteOldCoupons();
	}
}
