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
import com.parking.dto.ResetPasswordRequest;
import com.parking.entity.Login;
import com.parking.model.ResponseObject;
import com.parking.repository.LoginRepository;
import com.parking.service.EmailSenderService;
import com.parking.service.LoginService;
import com.parking.utils.EmailUtils;
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
	public ResponseObject forget(String username) throws MessagingException {
		Login login = loginRepository.findByUsername(username);
		if(ObjectUtils.isEmpty(login))
		{
			return new ResponseObject(HttpStatus.NOT_FOUND, "This account was not found.", null);
		}
		if(ObjectUtils.isEmpty(login.getEmail()))
		{
			return new ResponseObject(HttpStatus.NOT_ACCEPTABLE, "This account does not have an email.", null);
		}
		if(login.getStatus() == false)
		{
			return new ResponseObject(HttpStatus.BAD_REQUEST, "Please verify your account first.", null);
		}
		String verifyCode = VerificationCodeGenerator.generate();
		emailSenderService.sendVerificationEmail(login.getEmail(), login.getUsername(), verifyCode);
		loginRepository.setVerificationCode(username, verifyCode);
		VerifyCodeManager verifyCodeManager = new VerifyCodeManager();
		verifyCodeManager.scheduleVerificationCleanup(SessionConstant.OTP_EXPIRE_TIME * 1000, login.getUsername(), loginRepository);
		String email = EmailUtils.hide(login.getEmail());
		return new ResponseObject(HttpStatus.OK,
				"Your verification code has been sent to email address "+ email +". Please check it", null);
	}
	
	
	@Override
	@Transactional
	public ResponseObject resetPassword(ResetPasswordRequest request)
	{
		Login login = loginRepository.findByUsername(request.getUsername());
		if(ObjectUtils.isEmpty(login))
		{
			return new ResponseObject(HttpStatus.NOT_FOUND, "This login was not found.", null);
		}
		if(login.getStatus() == false)
		{
			return new ResponseObject(HttpStatus.UNAUTHORIZED, "Please verify your account first.", null);
		}
		if(!request.getVerificationCode().equals(login.getVerificationCode()))
		{
			return new ResponseObject(HttpStatus.BAD_REQUEST, "Your verification code is invalid.", null);
		}
		String hashPasword = passwordEncoder.encode(request.getPassword());
		loginRepository.setPassword(request.getUsername(),hashPasword);
		loginRepository.removeVerificationCode(request.getUsername());
		return new ResponseObject(HttpStatus.OK, "Your password has been reset successfully.", null);
	}
}
