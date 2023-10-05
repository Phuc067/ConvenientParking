package com.parking.service;

import java.util.List;

import javax.mail.MessagingException;

import com.parking.dto.LoginDto;
import com.parking.dto.ResponseLoginDto;
import com.parking.dto.VerificationDto;
import com.parking.entity.Login;
import com.parking.model.ResponseObject;

public interface LoginService {
	
	ResponseLoginDto doLogin(LoginDto loginDto);
	ResponseObject register(LoginDto loginDto) throws MessagingException;
	ResponseObject verification(VerificationDto verificationDto);
	List<Login> getAll();
}
