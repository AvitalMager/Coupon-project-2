package com.avital.coupons.api;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.avital.coupons.data.UserLoginData;
import com.avital.coupons.dto.CouponDto;
import com.avital.coupons.dto.CouponInfoDto;
import com.avital.coupons.enums.CouponCategory;
import com.avital.coupons.exceptions.ApplicationException;
import com.avital.coupons.logic.CouponsController;

@RestController
@RequestMapping("/coupon")
public class CouponsApi {

	@Autowired
	CouponsController couponsController; 
	
	@PostMapping
	public long createCoupon(@RequestBody CouponDto couponDto,HttpServletRequest request ) throws ApplicationException {
		
		//Receiving user login data
		UserLoginData userLoginData = (UserLoginData)request.getAttribute("userLoginData");
		
		//Receiving company id from user login data
		couponDto.setCompanyId(userLoginData.getCompanyId());
		
		//Execute create coupon
		long couponId = this.couponsController.createCoupon(couponDto);
		
		return couponId;
	}
	
	@PutMapping
	public void updateCoupon(@RequestBody CouponDto couponDto, HttpServletRequest request) throws ApplicationException {
		
		//Receiving user login data
		UserLoginData userLoginData = (UserLoginData)request.getAttribute("userLoginData");
		
		//Receiving company id from user login data
		couponDto.setCompanyId(userLoginData.getCompanyId());
		
		//Execute update coupon
		this.couponsController.updateCoupon(couponDto);
	}
	
	@DeleteMapping("/{couponId}")
	public void deleteCoupon(@PathVariable("couponId") long id) throws ApplicationException {
		
		//Execute delete coupon
		this.couponsController.deleteCoupon(id);
	}

	@GetMapping("/{couponId}")
	public CouponDto getCoupon(@PathVariable("couponId") long id ) throws ApplicationException {
		
		//This method shows all coupons information
		//Execute get coupon
		CouponDto couponDto = this.couponsController.getCoupon(id);
		
		return couponDto;
	}

	@GetMapping
	public List<CouponInfoDto> getAllCoupons() throws ApplicationException {
		
		//This method shows only general information, for more information you can use GET COUPON method
		//Execute get all coupons
		List<CouponInfoDto> coupons = this.couponsController.getAllCoupons();
		
		return coupons;
	}
	
	@GetMapping("/getAllCouponsByCompanyId")
	public List<CouponInfoDto> getAllCouponsByCompanyId(@RequestParam("companyId") long companyId) throws ApplicationException {
		
		//This method shows only general information, for more information you can use GET COUPON method
		//Execute get all coupons by company id
		List <CouponInfoDto> coupons = this.couponsController.getAllCouponsByCompanyId(companyId);
		
		return coupons;
	}
	
	@GetMapping("/getAllCouponsByType")
	public List<CouponInfoDto> getAllCouponsByType(@RequestParam("couponCategory") CouponCategory couponCategory) throws ApplicationException {
		
		//This method shows only general information, for more information you can use GET COUPON method
		//Execute get all coupons by type
		List <CouponInfoDto> coupons = this.couponsController.getAllCouponsByType(couponCategory);
		
		return coupons;
	}
	@GetMapping("/getPurchasedCouponsByMaxPrice")
	public List<CouponInfoDto> getPurchasedCouponsByMaxPrice(@RequestParam("maxPrice")float maxPrice,@RequestParam("userId") long userId) throws ApplicationException{
		
		//This method shows only general information, for more information you can use GET COUPON method
		//Execute get all purchased coupons by max price
		List <CouponInfoDto> coupons = this.couponsController.getPurchasedCouponsByMaxPrice(maxPrice, userId);
		return coupons;
	}
}
