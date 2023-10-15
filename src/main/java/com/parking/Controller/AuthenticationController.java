package com.parking.controller;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.parking.constant.SessionConstant;
import com.parking.dto.EmailRequest;
import com.parking.dto.LoginRequest;
import com.parking.dto.RegisterRequest;
import com.parking.dto.ResetPasswordRequest;
import com.parking.dto.RefreshTokenRequest;
import com.parking.dto.VerificationRequest;
import com.parking.model.ResponseObject;
import com.parking.service.EmailSenderService;
import com.parking.service.RefreshTokenService;
import com.parking.utils.VerificationCodeGenerator;
import com.parking.service.AuthenticationService;

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
	public ResponseEntity<?> doLogin(@RequestBody LoginRequest loginDto, HttpSession session) {
		ResponseObject responseObject = authenticationService.login(loginDto);
		return ResponseEntity.status(responseObject.getStatus()).body(responseObject);
	}

	@GetMapping(value = "/logout")
	public ResponseEntity<?> doGetLogOut(HttpSession session) {
		if (ObjectUtils.isNotEmpty(session.getAttribute(SessionConstant.CURRENT_USER))) {
			session.removeAttribute(SessionConstant.CURRENT_USER);
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject(HttpStatus.OK, "Logout successfully", null));
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(new ResponseObject(HttpStatus.BAD_REQUEST, "You hadn't loged in", null));
	}

	@PostMapping(value = "/register")
	public ResponseEntity<?> doRegister(@RequestBody RegisterRequest registerDto) throws MessagingException
	{
		ResponseObject responseObject = authenticationService.register(registerDto);
		return ResponseEntity.status(responseObject.getStatus()).body(responseObject);
	}
	
	@GetMapping(value = "/verification")
	public ResponseEntity<?> doGetVerification(@RequestParam String  username) throws MessagingException {
		ResponseObject responseObject = authenticationService.getNewVerification(username);
		return ResponseEntity.status(responseObject.getStatus()).body(responseObject);
	}
	
	@PostMapping(value = "/verification")
	public ResponseEntity<?> doVerification(@RequestBody VerificationRequest verificationDto) {
		ResponseObject responseObject = authenticationService.verification(verificationDto);
		return ResponseEntity.status(responseObject.getStatus()).body(responseObject);
	}
}
