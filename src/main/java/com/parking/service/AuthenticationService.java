package com.parking.service;

import java.util.List;

import javax.mail.MessagingException;

import com.parking.dto.LoginRequest;
import com.parking.dto.RegisterRequest;
import com.parking.dto.LoginResponse;
import com.parking.dto.VerificationRequest;
import com.parking.entity.Login;
import com.parking.model.AuthenticationResponse;
import com.parking.model.ResponseObject;

public interface AuthenticationService {
	
//	ResponseLoginDto doLogin(LoginDto loginDto);
	ResponseObject login(LoginRequest loginDto);
	ResponseObject register(RegisterRequest registerDto) throws MessagingException;
	ResponseObject getNewVerification(String username) throws MessagingException;
	ResponseObject verification(VerificationRequest verificationDto);
	ResponseObject forget(String username) throws MessagingException;
}
