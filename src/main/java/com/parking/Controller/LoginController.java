package com.parking.controller;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.parking.constant.SessionConstant;
import com.parking.dto.LoginDto;
import com.parking.dto.ResponseLoginDto;
import com.parking.model.ResponseObject;
import com.parking.service.LoginService;

@RestController
@CrossOrigin
@RequestMapping(value = "/api")
public class LoginController {

	@Autowired
	private LoginService loginService;

	@PostMapping(value = "/login")
	public ResponseEntity<ResponseObject> doLogin(@RequestBody LoginDto loginDto, HttpSession session) {
		if(ObjectUtils.isNotEmpty(session.getAttribute(SessionConstant.CURRENT_USER)))
		{
			return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body(new ResponseObject("You need to log out first", null));
		}
		ResponseLoginDto responseLoginDto = (ResponseLoginDto) loginService.doLogin(loginDto);
		if(ObjectUtils.isNotEmpty(responseLoginDto))
		{
			session.setAttribute(SessionConstant.CURRENT_USER, responseLoginDto.getObject());
			session.setAttribute(SessionConstant.CURRENT_ROLE, responseLoginDto.getRole());
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(new ResponseObject("Logged in successfully", null));
		}
		else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseObject("Login failed", null));
	}
	
}
