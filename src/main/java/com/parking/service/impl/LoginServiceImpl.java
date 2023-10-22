package com.parking.service.impl;

import java.util.List;

import javax.mail.MessagingException;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.parking.constant.SessionConstant;
import com.parking.dto.LoginRequest;
import com.parking.dto.ResetPasswordRequest;
import com.parking.entity.Login;
import com.parking.model.ResponseObject;
import com.parking.repository.LoginRepository;
import com.parking.service.EmailSenderService;
import com.parking.service.LoginService;
import com.parking.utils.VerificationCodeGenerator;
import com.parking.utils.VerifyCodeManager;

@Service
public class LoginServiceImpl  implements LoginService{
	
	@Autowired
	private LoginRepository loginRepository;
	
	@Autowired
	private EmailSenderService emailSenderService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public ResponseObject getAll()
	{
		List<Login> logins = loginRepository.findAll();
		if(logins.size() ==0)
		{
			return new ResponseObject(HttpStatus.NOT_FOUND, "Logins not found.", null);
		}
		return new ResponseObject(HttpStatus.OK, "Get all login successfully.", logins);
	}

	@Override
	@Transactional
	public ResponseObject changePassword(LoginRequest loginRequest) {
		
		Login login = loginRepository.findByUsername(loginRequest.getUsername());
		if(ObjectUtils.isEmpty(login))
		{
			return new ResponseObject(HttpStatus.NOT_FOUND, "This login was not found.", null);
		}
		if(login.getStatus() == false)
		{
			return new ResponseObject(HttpStatus.UNAUTHORIZED, "Please verify your account first.", null);
		}
		
		String hashPassword = passwordEncoder.encode(loginRequest.getPassword());
		loginRepository.setPassword(loginRequest.getUsername(), hashPassword);
		return new ResponseObject(HttpStatus.OK, "Your password has been reset successfully.", null);
	}
	
	
}
