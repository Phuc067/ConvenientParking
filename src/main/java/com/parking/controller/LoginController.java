package com.parking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.parking.dto.auth.LoginRequest;
import com.parking.model.ResponseObject;
import com.parking.service.LoginService;

@RestController
@RequestMapping("/api/login")
public class LoginController {
	
	@Autowired
	private LoginService loginService;
	
	@GetMapping("")
	public ResponseEntity<?> doGetLoginUser() {
		ResponseObject responseObject = loginService.getAll();
		return ResponseEntity.status(responseObject.getStatus()).body(responseObject);
	}
	
	@PostMapping("/change-password")
	public ResponseEntity<ResponseObject> doChangePassword(@RequestBody LoginRequest loginRequest)
	{
		ResponseObject responseObject = loginService.changePassword(loginRequest);
		return ResponseEntity.status(responseObject.getStatus()).body(responseObject);
	}
	
	
}
