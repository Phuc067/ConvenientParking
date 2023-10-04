package com.parking.controller;

import java.util.Random;
import java.util.random.RandomGenerator;

import javax.servlet.http.HttpServletRequest;
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

import com.parking.constant.ConfigConstant;
import com.parking.constant.SessionConstant;
import com.parking.dto.EmailDto;
import com.parking.dto.LoginDto;
import com.parking.dto.ResponseLoginDto;
import com.parking.model.ResponseObject;
import com.parking.security.VerificationCodeGenerator;
import com.parking.security.VerifyCodeManager;
import com.parking.service.EmailSenderService;
import com.parking.service.LoginService;

@RestController
@RequestMapping(value = "/api")
public class LoginController {

	@Autowired
	private LoginService loginService;

	@Autowired
	private EmailSenderService senderService;

	@PostMapping(value = "/login")
	public ResponseEntity<ResponseObject> doLogin(@RequestBody LoginDto loginDto, HttpSession session) {
		if (ObjectUtils.isNotEmpty(session.getAttribute(SessionConstant.CURRENT_USER))) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
					.body(new ResponseObject(HttpStatus.UNAUTHORIZED, "You need to log out first", null));
		}
		ResponseLoginDto responseLoginDto = (ResponseLoginDto) loginService.doLogin(loginDto);
		if (ObjectUtils.isNotEmpty(responseLoginDto.getObject())) {
			session.setAttribute(SessionConstant.CURRENT_USER, responseLoginDto.getObject());
			session.setAttribute(SessionConstant.CURRENT_ROLE, responseLoginDto.getRole());
			return ResponseEntity.status(HttpStatus.OK)
					.body(new ResponseObject(HttpStatus.OK, responseLoginDto.getMessage(), null));
		} else
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new ResponseObject(HttpStatus.BAD_REQUEST, responseLoginDto.getMessage(), null));
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

	@PostMapping(value = "/email")
	public ResponseEntity<?> doSendEmail(@RequestBody EmailDto emailDto, HttpSession session) {
		String verificationCode = VerificationCodeGenerator.generate();
		try {
			senderService.sendVerificationEmail(emailDto.getEmail(), verificationCode);
			session.setAttribute(SessionConstant.CURRENT_OTP, verificationCode);
			VerifyCodeManager verifyCodeManager = new VerifyCodeManager();
			verifyCodeManager.scheduleVerificationCleanup(SessionConstant.OTP_EXPIRE_TIME * 1000, session);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(HttpStatus.OK, "Email was sent successfully", verificationCode));
	}
	
	@PostMapping(value = "/verification")
	public ResponseEntity<?> doVerification(@RequestBody String  verifyCode, HttpSession session)
	{
		return null;
	}
}
