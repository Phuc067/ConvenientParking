package com.parking.controller;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.parking.constant.SessionConstant;
import com.parking.model.ResponseObject;

@RestController

@RequestMapping("")
public class HelloWorld {
	@GetMapping("/hello")
	public String doGetHelloWorld()
	{
		return "Hello world";
	}
	
	@GetMapping("/session")
	public ResponseEntity<?> doGetsession(HttpSession session)
	{
		
		String otp = (String) session.getAttribute(SessionConstant.CURRENT_OTP);
		if(ObjectUtils.isEmpty(otp))
		{
			return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body("session expired");
		}
		return ResponseEntity.ok(otp);
	}
}
