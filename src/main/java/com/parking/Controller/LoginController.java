package com.parking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.parking.model.ResponseObject;
import com.parking.service.LoginService;

@RestController
@RequestMapping("/api")
public class LoginController {
	
	@Autowired
	private LoginService loginService;
	
	@GetMapping("/logins")
	public ResponseEntity<?> doGetLoginUser() {
		ResponseObject responseObject = loginService.getAll();
		return ResponseEntity.status(responseObject.getStatus()).body(responseObject);
	}
}
