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
import com.avital.coupons.dto.PurchaseDto;
import com.avital.coupons.dto.PurchaseInfoDto;
import com.avital.coupons.exceptions.ApplicationException;
import com.avital.coupons.logic.PurchasesController;

@RestController
@RequestMapping("/purchase")
public class PurchaseApi {

	@Autowired
	PurchasesController purchasesController;

	@PostMapping
	public long createPurchase(@RequestBody PurchaseDto purchase, HttpServletRequest request)
			throws ApplicationException {

		//Receiving user login data
		UserLoginData userLoginData = (UserLoginData) request.getAttribute("userLoginData");

		//Receiving user id from user login data
		purchase.setUserId(userLoginData.getId());
		
		//Execute create purchase
		long purchaseId = this.purchasesController.createPurchase(purchase);

		return purchaseId;
	}

	@PutMapping
	public void updatePurchase(@RequestBody PurchaseDto purchase, HttpServletRequest request)
			throws ApplicationException {

		//Receiving user login data
		UserLoginData userLoginData = (UserLoginData) request.getAttribute("userLoginData");

		//Receiving user id from user login data
		purchase.setUserId(userLoginData.getId());
		
		//Execute update purchase
		this.purchasesController.updatePurchase(purchase);
	}

	@DeleteMapping("/{purchaseId}")
	public void deletePurchase(@PathVariable("purchaseId") long id) throws ApplicationException {

		//Execute delete purchase
		this.purchasesController.deletePurchase(id);
	}

	@GetMapping("/{purchaseId}")
	public PurchaseInfoDto getPurchase(@PathVariable("purchaseId") long id) throws ApplicationException {

		//Execute get purchase
		PurchaseInfoDto purchaseDto = this.purchasesController.getPurchase(id);

		return purchaseDto;
	}

	@GetMapping
	public List<PurchaseInfoDto> getAllPurchases() throws ApplicationException {

		//Execute get all purchases
		List<PurchaseInfoDto> purchases = this.purchasesController.getAllPurchases();

		return purchases;
	}

	@GetMapping("/getAllPurchasesByUserId")
	public List<PurchaseInfoDto> getAllPurchasesByUserId(HttpServletRequest request) throws ApplicationException {
		
		//Receiving user login data
		UserLoginData userLoginData = (UserLoginData) request.getAttribute("userLoginData");
		
		//Execute get all purchases by user id
		List<PurchaseInfoDto> purchases = this.purchasesController.getAllPurchasesByUserId(userLoginData.getId());

		return purchases;
	}

	
	@GetMapping("/getAllPurchasesByCompanyId")
	public List<PurchaseInfoDto> getAllPurchasesByCompanyId(@RequestParam("companyId") long companyId)
			throws ApplicationException {

		//Execute get all purchases by company id
		List<PurchaseInfoDto> purchases = this.purchasesController.getAllPurchasesByCompanyId(companyId);

		return purchases;
	}
}
