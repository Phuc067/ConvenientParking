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
import org.springframework.web.bind.annotation.RestController;

import com.parking.constant.SessionConstant;
import com.parking.dto.EmailDto;
import com.parking.dto.LoginDto;
import com.parking.dto.ResponseLoginDto;
import com.parking.dto.VerificationDto;
import com.parking.entity.Login;
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

	@GetMapping(value = "/loginUsers")
	public ResponseEntity<?> doGetLoginUser() {
		return ResponseEntity.ok(loginService.getAll());
	}

	@PostMapping(value = "/login")
	public ResponseEntity<ResponseLoginDto> doLogin(@RequestBody LoginDto loginDto, HttpSession session) {
		if (ObjectUtils.isNotEmpty(session.getAttribute(SessionConstant.CURRENT_USER))) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
					.body(new ResponseLoginDto(HttpStatus.UNAUTHORIZED, "you need to logout first", null, null));
		}
		ResponseLoginDto responseObject = loginService.doLogin(loginDto);
//		if(ObjectUtils.isNotEmpty(responseObject.getRole()))
//		{
//			session.setAttribute(SessionConstant.CURRENT_USER, responseObject.getObject());
//			session.setAttribute(SessionConstant.CURRENT_ROLE, responseObject.getRole());
//		}
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

	@PostMapping(value = "/email")
	public ResponseEntity<?> doSendEmail(@RequestBody EmailDto emailDto, HttpSession session) {
		String verificationCode = VerificationCodeGenerator.generate();

		try {
			senderService.sendVerificationEmail(emailDto.getEmail(), emailDto.getEmail(), verificationCode);
			session.setAttribute(SessionConstant.CURRENT_OTP, verificationCode);
//			VerifyCodeManager verifyCodeManager = new VerifyCodeManager();
//			verifyCodeManager.scheduleVerificationCleanup(SessionConstant.OTP_EXPIRE_TIME * 1000, session);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(new ResponseObject(HttpStatus.BAD_GATEWAY,
					"Unable to send email, please check your connection", null));
		}
		return ResponseEntity.status(HttpStatus.OK)
				.body(new ResponseObject(HttpStatus.OK, "Email was sent successfully", verificationCode));
	}

	@PostMapping(value = "/register")
	public ResponseEntity<?> doRegister(@RequestBody LoginDto loginDto) throws MessagingException
	{
		ResponseObject responseObject = loginService.register(loginDto);
		return ResponseEntity.status(responseObject.getStatus()).body(responseObject);
	}
	
	@PostMapping(value = "/verification")
	public ResponseEntity<?> doVerification(@RequestBody VerificationDto verificationDto) {
		ResponseObject responseObject = loginService.verification(verificationDto);
		return ResponseEntity.status(responseObject.getStatus()).body(responseObject);
	}
}
