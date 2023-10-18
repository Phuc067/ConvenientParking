package com.parking.controller;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.parking.constant.SessionConstant;
import com.parking.repository.LoginRepository;
import com.parking.utils.VerifyCodeManager;



@RestController

@RequestMapping("")
public class HelloWorld {
	@Autowired
	private LoginRepository loginRepository;
	
	@GetMapping("/hello")
	public String doGetHelloWorld()
	{
		return "Hello world";
	}
	
	@GetMapping("/session")
	public ResponseEntity<?> doGetsession(HttpSession session)
	{
		
		Enumeration<String> attributeNames = session.getAttributeNames();

		Map<String, Object> attributeMap = new HashMap<>();
		while (attributeNames.hasMoreElements()) {
		    String attributeName = attributeNames.nextElement();
		    Object attributeValue = session.getAttribute(attributeName);
		    attributeMap.put(attributeName, attributeValue);
		}
		return ResponseEntity.ok(attributeMap);
	}
	
	@GetMapping("/clear")
	public ResponseEntity<?> doClearVerificationCode(String username)
	{
		try {
			VerifyCodeManager verifyCodeManager = new VerifyCodeManager();
			verifyCodeManager.scheduleVerificationCleanup(SessionConstant.OTP_EXPIRE_TIME, username, loginRepository);
			return ResponseEntity.status(HttpStatus.OK).build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
}
