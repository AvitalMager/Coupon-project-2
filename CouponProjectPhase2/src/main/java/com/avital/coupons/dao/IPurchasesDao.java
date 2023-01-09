package com.avital.coupons.dao;


import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.avital.coupons.dto.PurchaseInfoDto;
import com.avital.coupons.entities.PurchaseEntity;

public interface IPurchasesDao extends CrudRepository <PurchaseEntity, Long> {

	//Get all purchases
	@Query("select new com.avital.coupons.dto.PurchaseInfoDto(p) from PurchaseEntity p")
	public List <PurchaseInfoDto> getAllPurchases();
	
	//get all purchases by user type
	@Query("select new com.avital.coupons.dto.PurchaseInfoDto(p) from PurchaseEntity p where p.user.id= :userId")
	public List<PurchaseInfoDto> getAllPurchasesByUserId(@Param("userId") long userId);
	
	//get all purchases by company id
	@Query("select new com.avital.coupons.dto.PurchaseInfoDto(p) from PurchaseEntity p where p.coupon.company.id= :companyId")
	public List<PurchaseInfoDto> getAllPurchasesByCompanyId(@Param("companyId")long companyId);
	
	@Transactional
	@Modifying
	//delete expired coupon purchases
	@Query("delete from PurchaseEntity p where p.coupon.id in(select c.id from CouponEntity c where c.endDate<= :endDate)")
	public void deleteExpiredCouponPurchases(@Param("endDate")Date endDate);
}
