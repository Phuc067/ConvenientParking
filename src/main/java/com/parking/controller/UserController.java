package com.parking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.parking.dto.user.UserInsert;
import com.parking.entity.User;
import com.parking.model.ResponseObject;
import com.parking.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/{username}")
	public ResponseEntity<?> doGetInformation(@PathVariable(name ="username") String username)
	{
		ResponseObject responseObject = userService.getByUsername(username);
		return ResponseEntity.status(responseObject.getStatus()).body(responseObject);
	}
	
	@PostMapping("")
	public ResponseEntity<?> doInsertUser(@RequestBody UserInsert user)
	{
		ResponseObject responseObject = userService.insert(user);
		return ResponseEntity.status(responseObject.getStatus()).body(responseObject);
	}

}
