package com.parking.controller;

import javax.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.parking.dto.auth.LoginRequest;
import com.parking.dto.auth.RegisterRequest;
import com.parking.dto.auth.ResetPasswordRequest;
import com.parking.dto.auth.VerificationRequest;
import com.parking.dto.refreshToken.RefreshTokenRequest;
import com.parking.model.ResponseObject;
import com.parking.service.RefreshTokenService;
import com.parking.service.AuthenticationService;


@CrossOrigin(origins = "${allowed.origin}")
@RestController
@RequestMapping(value = "/api/auth")
public class AuthenticationController {

	@Autowired
	private AuthenticationService authenticationService;

	@Autowired
	private RefreshTokenService refreshTokenService;
	@PostMapping("/refresh")
	public ResponseEntity<ResponseObject> getRefreshToken(@RequestBody RefreshTokenRequest request)
	{
		ResponseObject responseObject =  refreshTokenService.genarateAccessToken(request.getRefreshToken());
		return  ResponseEntity.status(responseObject.getStatus()).body(responseObject);
	}

	@PostMapping(value = "/login")
	public ResponseEntity<?> doLogin(@RequestBody LoginRequest loginDto) {
		ResponseObject responseObject = authenticationService.login(loginDto);
		return ResponseEntity.status(responseObject.getStatus()).body(responseObject);
	}
	
	@PostMapping(value = "/register")
	public ResponseEntity<?> doRegister(@RequestBody RegisterRequest registerDto) throws MessagingException
	{
		ResponseObject responseObject = authenticationService.register(registerDto);
		return ResponseEntity.status(responseObject.getStatus()).body(responseObject);
	}
	
	@GetMapping(value = "/verification")
	public ResponseEntity<?> doGetVerification(@RequestParam String username) throws MessagingException {
		ResponseObject responseObject = authenticationService.getNewVerification(username);
		return ResponseEntity.status(responseObject.getStatus()).body(responseObject);
	}
	
	@PostMapping(value = "/verification")
	public ResponseEntity<?> doVerification(@RequestBody VerificationRequest verificationDto) {
		ResponseObject responseObject = authenticationService.verification(verificationDto);
		return ResponseEntity.status(responseObject.getStatus()).body(responseObject);
	}
	
	@GetMapping(value = "/forget")
	public ResponseEntity<?> doGetPassword(@RequestParam String username) throws MessagingException 
	{
		ResponseObject responseObject = authenticationService.forget(username);
		return ResponseEntity.status(responseObject.getStatus()).body(responseObject);
	}
	
	@PostMapping(value = "/forget")
	public ResponseEntity<?> doResetPassword(@RequestBody ResetPasswordRequest request) throws MessagingException {
		ResponseObject responseObject = authenticationService.resetPassword(request);
		return ResponseEntity.status(responseObject.getStatus()).body(responseObject);
	}
}
