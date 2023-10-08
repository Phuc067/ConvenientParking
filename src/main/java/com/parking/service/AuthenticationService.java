package com.parking.service;

import java.util.List;

import javax.mail.MessagingException;

import com.parking.dto.LoginDto;
import com.parking.dto.RegisterDto;
import com.parking.dto.ResponseLoginDto;
import com.parking.dto.VerificationDto;
import com.parking.entity.Login;
import com.parking.model.AuthenticationResponse;
import com.parking.model.ResponseObject;

public interface AuthenticationService {
	
//	ResponseLoginDto doLogin(LoginDto loginDto);
	ResponseObject login(LoginDto loginDto);
	ResponseObject register(RegisterDto registerDto) throws MessagingException;
	ResponseObject verification(VerificationDto verificationDto);
	List<Login> getAll();
}
