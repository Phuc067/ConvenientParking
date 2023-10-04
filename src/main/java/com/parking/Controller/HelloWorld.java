package com.parking.controller;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



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
		
		Enumeration<String> attributeNames = session.getAttributeNames();

		Map<String, Object> attributeMap = new HashMap<>();
		while (attributeNames.hasMoreElements()) {
		    String attributeName = attributeNames.nextElement();
		    Object attributeValue = session.getAttribute(attributeName);
		    attributeMap.put(attributeName, attributeValue);
		}
		return ResponseEntity.ok(attributeMap);
	}
}
