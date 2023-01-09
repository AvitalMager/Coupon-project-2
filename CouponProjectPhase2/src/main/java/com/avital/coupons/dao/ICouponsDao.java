package com.avital.coupons.dao;


import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.avital.coupons.dto.CouponDto;
import com.avital.coupons.dto.CouponInfoDto;
import com.avital.coupons.entities.CouponEntity;
import com.avital.coupons.enums.CouponCategory;

public interface ICouponsDao extends CrudRepository<CouponEntity, Long> {

	public boolean existsByName(String name);

	//get all coupons
	@Query("select new com.avital.coupons.dto.CouponInfoDto(c) from CouponEntity c")
	public List<CouponInfoDto> getAllCoupons();

	//get all coupons by company id
	@Query("select new com.avital.coupons.dto.CouponInfoDto(c) from CouponEntity c where c.company.id= :companyId")
	public List<CouponInfoDto> getAllCouponsByCompanyId(@Param("companyId") long companyId);

	//get all coupons by type
	@Query("select new com.avital.coupons.dto.CouponInfoDto(c) from CouponEntity c where c.couponCategory= :couponCategory")
	public List<CouponInfoDto> getAllCouponsByType(@Param("couponCategory") CouponCategory couponCategory);

	//get all purchased coupons by max price
	@Query("select new com.avital.coupons.dto.CouponInfoDto(c) from CouponEntity c where c.price<= :maxPrice and c.id in(select p.coupon.id from PurchaseEntity p where p.user.id= :userId)")
	public List<CouponInfoDto> getPurchasedCouponsByMaxPrice(@Param("maxPrice") float maxPrice,
			@Param("userId") long userId);
	
	//delete old coupons 
	@Transactional
	@Modifying 
	@Query("delete from CouponEntity c where endDate <= :endDate")
	public void deleteOldCoupons(@Param("endDate")Date endDate);
	
	//get amount of available coupons
	@Query("select c.amount from CouponEntity c where c.id= :id")
	public int getAmountOfAvailableCoupons(@Param("id") long id);
}
