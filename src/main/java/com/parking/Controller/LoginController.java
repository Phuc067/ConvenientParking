package com.parking.controller;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.parking.dto.ResetPasswordRequest;
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
	
	@GetMapping(value = "/forget")
	public ResponseEntity<?> doGetPassword(@RequestParam String username) throws MessagingException 
	{
		ResponseObject responseObject = loginService.forget(username);
		return ResponseEntity.status(responseObject.getStatus()).body(responseObject);
	}
	
	@PostMapping(value = "/forget")
	public ResponseEntity<?> doResetPassword(@RequestBody ResetPasswordRequest request) throws MessagingException {
		ResponseObject responseObject = loginService.resetPassword(request);
		return ResponseEntity.status(responseObject.getStatus()).body(responseObject);
	}
}
