package com.avital.coupons.logic;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.avital.coupons.dao.IPurchasesDao;
import com.avital.coupons.dto.CouponDto;
import com.avital.coupons.dto.PurchaseDto;
import com.avital.coupons.dto.PurchaseInfoDto;
import com.avital.coupons.entities.CouponEntity;
import com.avital.coupons.entities.PurchaseEntity;
import com.avital.coupons.entities.UserEntity;
import com.avital.coupons.enums.ErrorType;
import com.avital.coupons.enums.UserType;
import com.avital.coupons.exceptions.ApplicationException;

@Controller
public class PurchasesController {

	@Autowired
	private CouponsController couponsController;

	@Autowired
	private UserController userController;

	@Autowired
	private IPurchasesDao purchasesDao;

	public PurchasesController() {

	}

	public long createPurchase(PurchaseDto purchaseDto) throws ApplicationException {

		try {
			
			//Receiving current time
			long millis = System.currentTimeMillis();
			Timestamp date = new Timestamp(millis);
			
			//Setting current time
			purchaseDto.setTimeOfPurchase(date);
			
			// Validating before creating purchase
			validateCreateOrUpdatePurchase(purchaseDto);

			// Converting purchase dto to purchase entity
			PurchaseEntity purchaseEntity = new PurchaseEntity(purchaseDto);

			// Receiving coupon data
			CouponDto couponDto = this.couponsController.getCoupon(purchaseDto.getCouponId());

			// Converting coupon dto to entity
			CouponEntity couponEntity = new CouponEntity(couponDto);

			//Receiving user entity
			UserEntity userEntity = this.userController.getUserEntity(purchaseDto.getUserId());
		
			// set user for this purchase
			purchaseEntity.setUser(userEntity);

			// set coupon for this purchase
			purchaseEntity.setCoupon(couponEntity);

			// Creating purchase
			this.purchasesDao.save(purchaseEntity);

			// Receiving purchase id
			long purchaseId = purchaseEntity.getId();

			// Calculating new coupons amount
			int couponsAmount = couponDto.getAmount() - purchaseDto.getAmount();

			// Setting new coupon amount
			couponDto.setAmount(couponsAmount);

			// Execute update coupon
			this.couponsController.updateCoupon(couponDto);

			return purchaseId;

		} catch (ApplicationException e) {

			// If there was an exception in "try" block above, it is caught here and
			// notifies the level above.
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR, purchaseDto.toString());
		}
	}

	public void updatePurchase(PurchaseDto purchaseDto) throws ApplicationException {

		try {
			// Validating before updating purchase
			validateCreateOrUpdatePurchase(purchaseDto);

			// Converting purchase dto to entity
			PurchaseEntity purchaseEntity = new PurchaseEntity(purchaseDto);

			//Receiving user entity
			UserEntity userEntity = this.userController.getUserEntity(purchaseDto.getUserId());

			// Set user for this purchase
			purchaseEntity.setUser(userEntity);

			// Receiving coupon data
			CouponDto couponDto = this.couponsController.getCoupon(purchaseDto.getCouponId());

			// Converting coupon dto to entity
			CouponEntity couponEntity = new CouponEntity(couponDto);

			// Set coupon for this purchase
			purchaseEntity.setCoupon(couponEntity);

			// Receiving new coupons amount
			couponDto.setAmount(couponEntity.getAmount() - purchaseDto.getAmount());

			// Creating purchase
			this.purchasesDao.save(purchaseEntity);

			// Execute update coupon
			this.couponsController.updateCoupon(couponDto);

		} catch (ApplicationException e) {

			// If there was an exception in "try" block above, it is caught here and
			// notifies the level above.
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR,
					"failed, can not update purchase details" + purchaseDto.toString());
		}
	}

	private void validateCreateOrUpdatePurchase(PurchaseDto purchase) throws ApplicationException {

		// Validating purchase user Id
		if (Long.valueOf(purchase.getUserId()) == null) {
			throw new ApplicationException(ErrorType.INVALID_ID, purchase.toString());
		}

		// Validating purchase coupon Id
		if (Long.valueOf(purchase.getCouponId()) == null) {
			throw new ApplicationException(ErrorType.INVALID_ID, purchase.toString());
		}

		// Validating purchase amount
		if (Long.valueOf(purchase.getAmount()) == null) {
			throw new ApplicationException(ErrorType.INVALID_AMOUNT, purchase.toString());
		}

		// Validating purchase time of purchase
		if (purchase.getTimeOfPurchase() == null) {
			throw new ApplicationException(ErrorType.INVALID_DATES, purchase.toString());
		}

		// Validating coupons expiration date
		checkCouponsExperationDate(purchase);

		// Validating coupons amount
		validateAmountOfAvailableCoupons(purchase);
		
		//Checking if this user is "CUSTOMER" type 
		isThisBuyerCustomerType(purchase);

	}

	public void deletePurchase(long id) throws ApplicationException {

		try {

			// Executing Delete Purchases
			this.purchasesDao.deleteById(id);

		} catch (Exception e) {

			// If there was an exception in "try" block above, it is caught here and
			// notifies the level above.
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR,
					"failed, purchase has been not deleted.Id: " + id);
		}
	}

	public List<PurchaseInfoDto> getAllPurchases() throws ApplicationException {

		try {

			// Executing get All Purchases
			List<PurchaseInfoDto> purchases = this.purchasesDao.getAllPurchases();

			return purchases;

		} catch (Exception e) {

			// If there was an exception in "try" block above, it is caught here and
			// notifies the level above.
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR, "failed, can not get AllPurchases details");
		}
	}

	public PurchaseInfoDto getPurchase(long id) throws ApplicationException {

		try {
			
			// Executing get Purchase
			PurchaseEntity purchaseEntity = this.purchasesDao.findById(id).get();

			// Converting purchase entity to dto
			PurchaseInfoDto purchaseDto = new PurchaseInfoDto(purchaseEntity);

			return purchaseDto;

		} catch (Exception e) {

			// If there was an exception in "try" block above, it is caught here and
			// notifies the level above.
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR,
					"failed, can not get purchase details. id: " + id);
		}
	}

	public List<PurchaseInfoDto> getAllPurchasesByUserId(long userId) throws ApplicationException {

		try {
			// Executing get All Purchases By User Id
			List<PurchaseInfoDto> purchases = this.purchasesDao.getAllPurchasesByUserId(userId);

			return purchases;

		} catch (Exception e) {

			// If there was an exception in "try" block above, it is caught here and
			// notifies the level above.
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR,
					"failed, can not get AllPurchasesByUserId details. Id: " + userId);
		}
	}

	public List<PurchaseInfoDto> getAllPurchasesByCompanyId(long companyId) throws ApplicationException {

		try {

			// Executing get All Purchases By Company Id
			List<PurchaseInfoDto> purchases = this.purchasesDao.getAllPurchasesByCompanyId(companyId);

			return purchases;

		} catch (Exception e) {

			// If there was an exception in "try" block above, it is caught here and
			// notifies the level above.
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR,
					"failed, can not get AllPurchasesByCompanyId details. Id: " + companyId);
		}
	}

	private void validateAmountOfAvailableCoupons(PurchaseDto purchase) throws ApplicationException {

		// Validating coupons amount
		if (purchase.getAmount() <= 0) {
			throw new ApplicationException(ErrorType.INVALID_AMOUNT, "This purchase amount is 0" + purchase.toString());
		}

		// Receiving amount of available coupons
		int amountOfAvailableCoupons = this.couponsController.getAmountOfAvailableCoupons(purchase.getCouponId());

		// Checking if there enough coupons for this purchase
		if (amountOfAvailableCoupons < purchase.getAmount()) {
			throw new ApplicationException(ErrorType.INVALID_AMOUNT, "Out of stock" + purchase.toString());
		}
	}

	private void checkCouponsExperationDate(PurchaseDto purchases) throws ApplicationException {

		// Creating today's date
		long millis = System.currentTimeMillis();
		Date date = new Date(millis);

		// Receiving coupons expiration date
		Date endDate = this.couponsController.getCoupon(purchases.getCouponId()).getEndDate();

		// Checking if this coupon expired
		if (date.after(endDate)) {
			throw new ApplicationException(ErrorType.COUPON_EXPIERED, "expired coupon" + purchases.toString());
		}
		// Printing coupons expiration date just to make sure
		System.out.println(endDate);
	}
	
	public void deleteExpiredCouponPurchases() throws ApplicationException {

		try {
			//Receiving current date
			long millis = System.currentTimeMillis();
			Date date = new Date(millis);

			//Execute delete old coupons
			this.purchasesDao.deleteExpiredCouponPurchases(date);

		} catch (Exception e) {
			// If there was an exception in "try" block above, it is caught here and
			// notifies the level above.
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR, "failed, can not delete old purchases");
		}
	}
	
	private void isThisBuyerCustomerType(PurchaseDto purchaseDto) throws ApplicationException {
		
		//Receiving user id
		long userId = purchaseDto.getUserId();
		
		//Checking id this user is user type "customer"
		if (userController.getUser(userId).getUserType() != UserType.CUSTOMER) {
			throw new ApplicationException(ErrorType.NO_PERMISSION_GRANTED, ErrorType.NO_PERMISSION_GRANTED.getErrorMessage());
		}
	}
}
