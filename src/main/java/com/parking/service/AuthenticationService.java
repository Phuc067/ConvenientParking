package com.parking.service;


import javax.mail.MessagingException;

import com.parking.dto.auth.LoginRequest;
import com.parking.dto.auth.RegisterRequest;
import com.parking.dto.auth.ResetPasswordRequest;
import com.parking.dto.auth.VerificationRequest;
import com.parking.model.ResponseObject;

public interface AuthenticationService {
	
//	ResponseLoginDto doLogin(LoginDto loginDto);
	ResponseObject login(LoginRequest loginDto);
	ResponseObject register(RegisterRequest registerDto) throws MessagingException;
	ResponseObject getNewVerification(String username) throws MessagingException;
	ResponseObject verification(VerificationRequest verificationDto);
	ResponseObject forget(String username) throws MessagingException;
	ResponseObject resetPassword(ResetPasswordRequest request);
}
