package com.avital.coupons.logic;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.avital.coupons.dao.ICouponsDao;
import com.avital.coupons.dto.CompanyDto;
import com.avital.coupons.dto.CouponDto;
import com.avital.coupons.dto.CouponInfoDto;
import com.avital.coupons.entities.CompanyEntity;
import com.avital.coupons.entities.CouponEntity;
import com.avital.coupons.enums.CouponCategory;
import com.avital.coupons.enums.ErrorType;
import com.avital.coupons.exceptions.ApplicationException;

@Controller
public class CouponsController {

	@Autowired
	private CompaniesController companiesController;

	@Autowired
	private ICouponsDao couponsDao;

	public CouponsController() {
	}

	public long createCoupon(CouponDto couponDto) throws ApplicationException {

		try {
			// Checking if coupon exists
			validateIsCouponExistss(couponDto);

			// Validating before creating coupon
			validateCreateOrUpdateCoupon(couponDto);

			// Creating company entity
			CouponEntity couponEntity = new CouponEntity(couponDto);

			// Saving company data in to company dto
			CompanyDto companyDto = this.companiesController.getCompany(couponDto.getCompanyId());

			// Creating company entity
			CompanyEntity companyEntity = new CompanyEntity(companyDto);

			// Setting company id
			couponEntity.setCompany(companyEntity);

			// Execute create coupon
			long couponId = this.couponsDao.save(couponEntity).getId();

			return couponId;
			
		} catch (ApplicationException e) {
			
			// If there was an exception in "try" block above, it is caught here and
			// notifies the level above.
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR, couponDto.toString());
		}
	}

	public void updateCoupon(CouponDto couponDto) throws ApplicationException {

		try {
			// Validating before updating coupon
			validateCreateOrUpdateCoupon(couponDto);

			// Converting coupon dto to entity
			CouponEntity couponEntity = new CouponEntity(couponDto);

			// Saving company data in to dto
			CompanyDto companyDto = this.companiesController.getCompany(couponDto.getCompanyId());

			// Converting company dto to company entity
			CompanyEntity companyEntity = new CompanyEntity(companyDto);

			// Setting company id
			couponEntity.setCompany(companyEntity);

			// Execute update coupon
			this.couponsDao.save(couponEntity).getId();

		} catch (ApplicationException e) {

			// If there was an exception in "try" block above, it is caught here and
			// notifies the level above.
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR,
					"failed, can not update coupon details" + couponDto.toString());
		}
	}

	private void validateCreateOrUpdateCoupon(CouponDto coupon) throws ApplicationException {

		// Validating coupons name
		if (coupon.getName().isEmpty()) {
			throw new ApplicationException(ErrorType.MUST_ENTER_NAME, coupon.getName());
		}

		// Validating coupons name
		if (coupon.getName().length() < 2) {
			throw new ApplicationException(ErrorType.NAME_TOO_SHORT, coupon.getName());
		}

		// Validating coupons price
		if (coupon.getPrice() == 0) {
			throw new ApplicationException(ErrorType.INVALID_PRICE, coupon.toString());
		}

		// Validating coupons date
		if (coupon.getStartDate() == null) {
			throw new ApplicationException(ErrorType.INVALID_DATES, coupon.toString());
		}

		// Validating coupons date
		if (coupon.getEndDate() == null) {
			throw new ApplicationException(ErrorType.INVALID_DATES, coupon.toString());
		}

		// Validating coupons category
		if (coupon.getCouponCategory() == null) {
			throw new ApplicationException(ErrorType.INVALID_COUPON_TYPE, coupon.toString());
		}

		// Validating coupons amount
		if (Long.valueOf(coupon.getAmount()) == null) {
			throw new ApplicationException(ErrorType.INVALID_AMOUNT, coupon.toString());
		}

		// Validating coupons company Id
		if (Long.valueOf(coupon.getCompanyId()) == null) {
			throw new ApplicationException(ErrorType.INVALID_ID, coupon.toString());
		}

	}

	private void validateIsCouponExistss(CouponDto couponsDto) throws ApplicationException {

		// Validating if coupon name exists
		if (this.couponsDao.existsByName(couponsDto.getName())) {
			throw new ApplicationException(ErrorType.COUPON_TITLE_EXIST, couponsDto.getName());
		}
	}

	public void deleteCoupon(long id) throws ApplicationException {

		try {
			// Deleting coupons
			this.couponsDao.deleteById(id);

		} catch (Exception e) {

			// If there was an exception in "try" block above, it is caught here and
			// notifies the level above.
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR,
					"failed, company has been not deleted.Id: " + id);
		}
	}

	public CouponDto getCoupon(long id) throws ApplicationException {

		try {
			// Execute get Coupon
			CouponEntity couponEntity = this.couponsDao.findById(id).get();

			// Converting coupon entity to coupon dto
			CouponDto couponDto = new CouponDto(couponEntity);

			return couponDto;

		} catch (Exception e) {

			// If there was an exception in "try" block above, it is caught here and
			// notifies the level above.
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR,
					"failed, can not get coupon details. coupon id: " + id);
		}
	}

	public List<CouponInfoDto> getAllCoupons() throws ApplicationException {

		try {
			// Execute get All Coupons
			List<CouponInfoDto> coupons = this.couponsDao.getAllCoupons();

			return coupons;

		} catch (Exception e) {

			// If there was an exception in "try" block above, it is caught here and
			// notifies the level above.
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR, "failed, can not get allCoupons details");
		}
	}

	public List<CouponInfoDto> getAllCouponsByCompanyId(long id) throws ApplicationException {

		try {
			// Execute get All Coupons By Company Id
			List<CouponInfoDto> coupons = this.couponsDao.getAllCouponsByCompanyId(id);

			return coupons;

		} catch (Exception e) {

			// If there was an exception in "try" block above, it is caught here and
			// notifies the level above.
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR,
					"failed, can not get AllCouponsByCompanyId details. Id: " + id);
		}
	}

	public List<CouponInfoDto> getAllCouponsByType(CouponCategory couponCategory) throws ApplicationException {

		try {
			// Execute get All Coupons By Type
			List<CouponInfoDto> coupons = this.couponsDao.getAllCouponsByType(couponCategory);

			return coupons;

		} catch (Exception e) {

			// If there was an exception in "try" block above, it is caught here and
			// notifies the level above.
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR,
					"failed, can not get AllCouponsByType details" + couponCategory);
		}
	}

	public int getAmountOfAvailableCoupons(long id) throws ApplicationException {

		try {
			// Execute get Amount Of Available Coupons
			return this.couponsDao.getAmountOfAvailableCoupons(id);

		} catch (Exception e) {

			// If there was an exception in "try" block above, it is caught here and
			// notifies the level above.
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR,
					"failed, can not get coupon details. coupon id: " + id);
		}
	}

	public List<CouponInfoDto> getPurchasedCouponsByMaxPrice(float maxPrice, long userId) throws ApplicationException {

		try {
			// Execute get purchased coupons by max price
			List<CouponInfoDto> coupons = this.couponsDao.getPurchasedCouponsByMaxPrice(maxPrice, userId);

			return coupons;

		} catch (Exception e) {

			// If there was an exception in "try" block above, it is caught here and
			// notifies the level above.
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR,
					"failed, can not get PurchasedCouponsByMaxPrice details");
		}
	}

	public void deleteOldCoupons() throws ApplicationException {

		try {
			// Receiving current date
			long millis = System.currentTimeMillis();
			Date date = new Date(millis);

			// Execute delete old coupons
			this.couponsDao.deleteOldCoupons(date);

		} catch (Exception e) {
			// If there was an exception in "try" block above, it is caught here and
			// notifies the level above.
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR, "failed, can not delete old coupons");
		}
	}
}
