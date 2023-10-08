package com.parking.service.impl;

import java.util.List;
import javax.mail.MessagingException;
import javax.transaction.Transactional;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.parking.constant.RoleConstant;
import com.parking.constant.SessionConstant;
import com.parking.dto.LoginDto;
import com.parking.dto.RegisterDto;
import com.parking.dto.VerificationDto;
import com.parking.entity.Login;
import com.parking.entity.Role;
import com.parking.model.AuthenticationResponse;
import com.parking.model.ResponseObject;
import com.parking.repository.LoginRepository;
import com.parking.repository.RoleRepository;
import com.parking.security.VerificationCodeGenerator;
import com.parking.security.VerifyCodeManager;
import com.parking.service.EmailSenderService;
import com.parking.service.JwtService;
import com.parking.service.AuthenticationService;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
	@Autowired
	private LoginRepository loginRepository;

	@Autowired
	private EmailSenderService emailSenderService;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private JwtService jwtService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Override
	public ResponseObject login(LoginDto loginDto) {
		Login login = loginRepository.findByUsername(loginDto.getUsername());
		if(!login.getStatus())
		{
			return new ResponseObject(HttpStatus.UNAUTHORIZED, "Please verify your account first",null);
		}
		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));

		String jwtToken = jwtService.generateToken(login);
		return new ResponseObject(HttpStatus.OK, "Login successfully", new AuthenticationResponse(jwtToken, RoleConstant.roleMap.get(login.getRole().getName())));

	}

	@Override
	@Transactional
	public ResponseObject register(RegisterDto registerDto) throws MessagingException {
		if (ObjectUtils.isNotEmpty(loginRepository.findByUsername(registerDto.getUsername()))) {
			return new ResponseObject(HttpStatus.BAD_REQUEST,"Username already exists", null);
		}
		if (ObjectUtils.isNotEmpty(loginRepository.findByEmail(registerDto.getEmail()))) {
			return  new ResponseObject(HttpStatus.BAD_REQUEST,"Email is already in use by someone else", null);
		}
		String verifyCode = VerificationCodeGenerator.generate();
		String hashPassword = passwordEncoder.encode(registerDto.getPassword());
		Login login = new Login();
		login.setUsername(registerDto.getUsername());
		login.setPassword(hashPassword);
		login.setEmail(registerDto.getEmail());
		login.setStatus(false);
		login.setVerificationCode(verifyCode);
		Role role = roleRepository.findByName(registerDto.getRole());
		login.setRole(role);
		loginRepository.save(login);
		emailSenderService.sendVerificationEmail(registerDto.getEmail(), registerDto.getUsername(), verifyCode);
		VerifyCodeManager verifyCodeManager = new VerifyCodeManager();
		verifyCodeManager.scheduleVerificationCleanup(SessionConstant.OTP_EXPIRE_TIME * 1000, registerDto.getUsername(),
				loginRepository);
		String jwtToken = jwtService.generateToken(login);
		return new ResponseObject(HttpStatus.CREATED, "Login was created successfully. Please check your email to get verification code.", jwtToken);
	}
	
	@Transactional
	public ResponseObject verification(VerificationDto verificationDto) {
		Login login = loginRepository.findByUsername(verificationDto.getUsername());

		if (login.getStatus() == true) {
			return new ResponseObject(HttpStatus.UNAUTHORIZED,
					"Your account has already been authenticated. You don't need to authenticate further, maybe.",
					null);
		}
		String verificationCode = new String();
		if (ObjectUtils.isNotEmpty(login)) {
			verificationCode = login.getVerificationCode();
		} else
			return new ResponseObject(HttpStatus.UNAUTHORIZED, "You have not sent an authentication request", null);
		System.out.println(verificationCode);
		System.out.println(verificationDto.getVerificationCode());

		if (ObjectUtils.isNotEmpty(verificationDto.getVerificationCode()) && ObjectUtils.isNotEmpty(verificationCode)
				&& verificationDto.getVerificationCode().equals(verificationCode)) {
			loginRepository.setStatus(verificationDto.getUsername());
			return new ResponseObject(HttpStatus.OK, "Verified successfully", null);
		}

		return new ResponseObject(HttpStatus.BAD_REQUEST, "Verified  failed", null);
	}

	@Override
	public List<Login> getAll() {

		return loginRepository.findAll();
	}
}
