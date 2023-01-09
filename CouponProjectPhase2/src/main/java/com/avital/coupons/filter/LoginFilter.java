package com.avital.coupons.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.avital.coupons.data.UserLoginData;
import com.avital.coupons.logic.CacheController;

@Component
public class LoginFilter implements Filter{

	@Autowired
	private CacheController cacheController;
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		//Creating  HttpServletRequest
		HttpServletRequest req = (HttpServletRequest) request;
		
		//Receiving URL request as String 
		String pageRequested = req.getRequestURL().toString();
		
		if (pageRequested.endsWith("/login")) {
			chain.doFilter(request, response);
			return;
		}
		
		if (pageRequested.endsWith("/user") && req.getMethod().toString().equals("POST")) {
			chain.doFilter(request, response);
			return;
		}
		
		//Receiving token from Authorization
		String token = req.getHeader("Authorization");
		
		//Receiving token from user login data
		UserLoginData userLoginData = (UserLoginData) cacheController.get(token);
		
		//Checking id user login data is not null 
		if (userLoginData!= null) {
			
			request.setAttribute("userLoginData", userLoginData);
			//Confirm successful login 
			//Move forward to the next filter in chain
			chain.doFilter(request, response);
			return;
		}
		
		HttpServletResponse res = (HttpServletResponse) response;
		//Can't logg in
		res.setStatus(401);
	}
}
