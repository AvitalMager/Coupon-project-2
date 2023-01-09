package com.avital.coupons.exceptions;

import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.avital.coupons.beans.ErrorBean;
import com.avital.coupons.enums.ErrorType;

// Exception handler class
@RestControllerAdvice
public class ExceptionsHandler {

	//	Response - Object in Spring
	@ExceptionHandler
	@ResponseBody
	// Variable name is throwable in order to remember that it handles Exception and Error
	public ErrorBean toResponse(Throwable throwable, HttpServletResponse response) {
		
		//	ErrorBean errorBean;
		if(throwable instanceof ApplicationException) {
		
			ApplicationException appException = (ApplicationException) throwable;

			//Receiving error type
			ErrorType errorType = appException.getErrorType(); 
			
			//Receiving error number
			int errorNumber = errorType.getErrorNumber();
			
			//Receiving error message
			String errorMessage = errorType.getErrorMessage();
			
			//Receiving error name 
			String errorName = errorType.getErrorMessage();
			
			//Setting error number
			response.setStatus(errorNumber);	

			//Creating error bean
			ErrorBean errorBean = new ErrorBean(errorNumber, errorName ,errorMessage); 
			
			//If the exception is to show
			if(appException.getErrorType().isShowStackTrace()) {
				
				//Sending exception
				appException.printStackTrace();
			}
			return errorBean;
		}
		
		response.setStatus(600);

		//Getting message from throwable
		String errorMessage = throwable.getMessage();
		
		//Creating error bean
		ErrorBean errorBean = new ErrorBean(601, "General error", errorMessage);
		
		//Sending the error
		throwable.printStackTrace();

		return errorBean;
	}
}