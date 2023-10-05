package com.parking.service.impl;


import java.util.List;

import javax.mail.MessagingException;
import javax.transaction.Transactional;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.parking.constant.RoleConstant;
import com.parking.constant.SessionConstant;
import com.parking.constant.SystemAdmin;
import com.parking.dto.LoginDto;
import com.parking.dto.ResponseLoginDto;
import com.parking.dto.VerificationDto;
import com.parking.entity.Login;
import com.parking.model.ResponseObject;
import com.parking.repository.LoginRepository;
import com.parking.security.VerificationCodeGenerator;
import com.parking.security.VerifyCodeManager;
import com.parking.service.EmailSenderService;
import com.parking.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {
	@Autowired
	private LoginRepository loginRepository;

	@Autowired
	private EmailSenderService emailSenderService;
	
	private BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();
	@Override
	public ResponseLoginDto doLogin(LoginDto loginDto) {
		
		if(loginDto.getUsername().equals( SystemAdmin.login.getUsername())  && loginDto.getPassword().equals(SystemAdmin.login.getPassword()))
		{
			ResponseLoginDto responseLoginDto = new ResponseLoginDto(HttpStatus.OK,"Logged in successfully", RoleConstant.ADMIN, "abc123");
			return responseLoginDto;
		}
		
		Login login = loginRepository.findByUsername(loginDto.getUsername());
		if(ObjectUtils.isEmpty(login))
		{
			return new ResponseLoginDto(HttpStatus.BAD_REQUEST,"Username or password was wrong",null,null);
		}
		
		if (bCrypt.matches(loginDto.getPassword(), login.getPassword())) {
			
			if (login.getRole().getName().equals(RoleConstant.MERCHANT_NAME)) {
				ResponseLoginDto responseLoginDto = new ResponseLoginDto(HttpStatus.OK,"Logged in successfully", RoleConstant.MERCHANT, "abc123");
				return responseLoginDto;
			}
			else if (login.getRole().getName().equals(RoleConstant.EMPLOYEE_NAME)) {
				ResponseLoginDto responseLoginDto = new ResponseLoginDto(HttpStatus.OK,"Logged in successfully", RoleConstant.EMPLOYEE, "abc123");
				return responseLoginDto;
			}
			else if(login.getRole().getName().equals(RoleConstant.USER_NAME)) {
				ResponseLoginDto responseLoginDto = new ResponseLoginDto(HttpStatus.OK,"Logged in successfully", RoleConstant.USER, "abc123");
				return responseLoginDto;
			}
		}
		return new ResponseLoginDto(HttpStatus.BAD_REQUEST,"Username or password was wrong",null,null);
	}
	@Override
	@Transactional
	public ResponseObject register(LoginDto loginDto) throws MessagingException {
		if (ObjectUtils.isNotEmpty(loginRepository.findByUsername(loginDto.getUsername()))) {
			return new ResponseObject(HttpStatus.BAD_REQUEST, "Username already exists", null);
		}
		if(ObjectUtils.isNotEmpty(loginRepository.findByEmail(loginDto.getEmail())))
		{
			return new ResponseObject(HttpStatus.BAD_REQUEST, "email is already in use by someone else", null);
		}
		String verifyCode = VerificationCodeGenerator.generate();
		String hashPassword = bCrypt.encode(loginDto.getPassword());
		loginRepository.insert(loginDto.getUsername(), hashPassword,loginDto.getEmail(),verifyCode, 3L);
		emailSenderService.sendVerificationEmail(loginDto.getEmail(), loginDto.getUsername(), verifyCode);
		VerifyCodeManager verifyCodeManager = new VerifyCodeManager();
		verifyCodeManager.scheduleVerificationCleanup(SessionConstant.OTP_EXPIRE_TIME * 1000, loginDto.getUsername(), loginRepository);
		return new ResponseObject(HttpStatus.OK, "Login was created successfully. Please check your email to get verification code.", null);
	}
	
	@Transactional
	public ResponseObject verification(VerificationDto verificationDto)
	{	
		Login login = loginRepository.findByUsername(verificationDto.getUsername());
		
		if(login.getStatus() == true)
		{
			return new ResponseObject(HttpStatus.UNAUTHORIZED,"Your account has already been authenticated. You don't need to authenticate further, maybe.", null);
		}
		String verificationCode = new String() ;
		if(ObjectUtils.isNotEmpty(login))
		{
			verificationCode = login.getVerificationCode();
		}
		else return new ResponseObject(HttpStatus.UNAUTHORIZED,"You have not sent an authentication request", null);
		System.out.println(verificationCode);
		System.out.println(verificationDto.getVerificationCode());
		
		if (ObjectUtils.isNotEmpty(verificationDto.getVerificationCode())
				&& ObjectUtils.isNotEmpty(verificationCode)
				&& verificationDto.getVerificationCode().equals(verificationCode)) {
			loginRepository.setStatus(verificationDto.getUsername());
			return new ResponseObject(HttpStatus.OK,"Verified successfully", null);
		}
		
		return new ResponseObject(HttpStatus.BAD_REQUEST,"Verified  failed", null);
	}
	
	@Override
	public List<Login> getAll() {
		
		
		return loginRepository.findAll();
	}
}
