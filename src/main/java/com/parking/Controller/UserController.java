package com.parking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.parking.model.ResponseObject;
import com.parking.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/information")
	public ResponseEntity<?> doGetInformation(@RequestParam String username)
	{
		ResponseObject responseObject = userService.getByUsername(username);
		return ResponseEntity.status(responseObject.getStatus()).body(responseObject);
	}

}
