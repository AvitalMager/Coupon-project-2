package com.avital.coupons.beans;

public class ErrorBean {

	private int errorNumber;
	private String errorName;
	private String errorMassage;
	
	public ErrorBean(int errorNumber, String errorName, String errorMassage) {
	
		this.errorNumber = errorNumber;
		this.errorName = errorName;
		this.errorMassage = errorMassage;
	}

	public int getErrorNumber() {
		return errorNumber;
	}

	public void setErrorNumber(int errorNumber) {
		this.errorNumber = errorNumber;
	}

	public String getErrorName() {
		return errorName;
	}

	public void setErrorName(String errorName) {
		this.errorName = errorName;
	}

	public String getErrorMassage() {
		return errorMassage;
	}

	public void setErrorMassage(String errorMassage) {
		this.errorMassage = errorMassage;
	}
}
