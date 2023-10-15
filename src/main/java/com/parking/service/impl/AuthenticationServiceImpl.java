package com.parking.service.impl;
import javax.mail.MessagingException;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.parking.constant.SessionConstant;
import com.parking.dto.LoginRequest;
import com.parking.dto.RegisterRequest;
import com.parking.dto.ResetPasswordRequest;
import com.parking.dto.VerificationRequest;
import com.parking.entity.Login;
import com.parking.entity.RefreshToken;
import com.parking.entity.Role;
import com.parking.model.AuthenticationResponse;
import com.parking.model.ResponseObject;
import com.parking.repository.LoginRepository;
import com.parking.repository.RoleRepository;
import com.parking.service.EmailSenderService;
import com.parking.service.JwtService;
import com.parking.service.RefreshTokenService;
import com.parking.utils.EmailUtils;
import com.parking.utils.VerificationCodeGenerator;
import com.parking.utils.VerifyCodeManager;
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
	private RefreshTokenService refreshTokenService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Override
	public ResponseObject login(LoginRequest loginDto) {
		Login login = loginRepository.findByUsername(loginDto.getUsername());
		if (ObjectUtils.isNotEmpty(login) && !login.getStatus()) {
			return new ResponseObject(HttpStatus.UNAUTHORIZED, "Please verify your account first", null);
		}
		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));

		RefreshToken refreshToken = refreshTokenService.createRefreshToken(loginDto.getUsername());
		
		String jwtToken = jwtService.generateToken(login);
		return new ResponseObject(HttpStatus.OK, "Login successfully", new AuthenticationResponse(jwtToken,
				refreshToken.getToken()));
	}

	@Override
	@Transactional
	public ResponseObject register(RegisterRequest registerDto) throws MessagingException {
		if (ObjectUtils.isNotEmpty(loginRepository.findByUsername(registerDto.getUsername()))) {
			return new ResponseObject(HttpStatus.CONFLICT, "The username already exists.", null);
		}
		if (ObjectUtils.isNotEmpty(loginRepository.findByEmail(registerDto.getEmail()))) {
			return new ResponseObject(HttpStatus.PRECONDITION_FAILED, "Email is already being used by someone else.", null);
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
		String email = EmailUtils.hide(registerDto.getEmail());
		return new ResponseObject(HttpStatus.CREATED,
				"The login was created successfully. Please check email " + email + " to get a verification code.", jwtToken);
	}

	@Transactional
	public ResponseObject verification(VerificationRequest verificationDto) {
		Login login = loginRepository.findByUsername(verificationDto.getUsername());

		if (login.getStatus() == true) {
			return new ResponseObject(HttpStatus.GONE,
					"Your account has already been authenticated. You don't need to authenticate further, maybe.",
					null);
		}
		String verificationCode = new String();
		if (ObjectUtils.isNotEmpty(login)) {
			verificationCode = login.getVerificationCode();
		} else
			return new ResponseObject(HttpStatus.UNAUTHORIZED, "You have not sent an authentication request.", null);
		if (ObjectUtils.isNotEmpty(verificationDto.getVerificationCode()) && ObjectUtils.isNotEmpty(verificationCode)
				&& verificationDto.getVerificationCode().equals(verificationCode)) {
			loginRepository.setStatus(verificationDto.getUsername());
			loginRepository.removeVerificationCode(verificationDto.getUsername());
			return new ResponseObject(HttpStatus.OK, "Verified successfully", null);
		}
		return new ResponseObject(HttpStatus.BAD_REQUEST, "Verified  failed", null);
	}

	@Override
	@Transactional
	public ResponseObject getNewVerification(String username) throws MessagingException {
		Login login = loginRepository.findByUsername(username);
		if(ObjectUtils.isEmpty(login))
		{
			return new ResponseObject(HttpStatus.NOT_FOUND, "This login was not found.", null);
		}
		
		if(login.getStatus() == true)
		{
			return new ResponseObject(HttpStatus.BAD_REQUEST, "Your account has already been authenticated. You don't need to authenticate further, maybe.", null);
		}
		
		String verifyCode = VerificationCodeGenerator.generate();
		emailSenderService.sendVerificationEmail(login.getEmail(), login.getUsername(), verifyCode);
		loginRepository.setVerificationCode(username, verifyCode);
		VerifyCodeManager verifyCodeManager = new VerifyCodeManager();
		verifyCodeManager.scheduleVerificationCleanup(SessionConstant.OTP_EXPIRE_TIME * 1000, login.getUsername(),
				loginRepository);
		String email = EmailUtils.hide(login.getEmail());
		return new ResponseObject(HttpStatus.OK,
				"Your verification code has been sent to email address "+ email +". Please check it", null);
	}

}