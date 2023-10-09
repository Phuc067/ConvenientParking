package com.parking.service.impl;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.parking.constant.SessionConstant;
import com.parking.dto.EmployeeRequest;
import com.parking.model.ResponseObject;
import com.parking.repository.EmployeeRepository;
import com.parking.repository.LoginRepository;
import com.parking.security.VerificationCodeGenerator;
import com.parking.security.VerifyCodeManager;
import com.parking.service.EmailSenderService;
import com.parking.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private EmailSenderService emailSenderService;

	@Autowired
	private LoginRepository loginRepository;

	private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

	@Override
	@Transactional
	public ResponseObject add(EmployeeRequest employeeDto) {
		try {
			employeeRepository.insert(employeeDto);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseObject(HttpStatus.BAD_REQUEST, e.getMessage(), null);
		}
		return new ResponseObject(HttpStatus.OK, "Employee was added successfully", null);
	}

	@Override
	@Transactional(value = TxType.REQUIRED)
	public ResponseObject edit(EmployeeRequest employeeDto) {
		employeeRepository.update(employeeDto);
		return new ResponseObject(HttpStatus.ACCEPTED, "Employee information was changed successfully", null);
	}

	@Override
	@Transactional(value = TxType.REQUIRED)
	public ResponseObject register(EmployeeRequest employeeDto, HttpSession session) throws MessagingException {
		this.add(employeeDto);
		String hashPassword = bCryptPasswordEncoder.encode(employeeDto.getPassword());
		if (ObjectUtils.isNotEmpty(loginRepository.findByUsername(employeeDto.getUsername()))) {
			return new ResponseObject(HttpStatus.BAD_REQUEST, "Username already exists", null);
		}
		String verifyCode = VerificationCodeGenerator.generate();
		emailSenderService.sendVerificationEmail(employeeDto.getEmail(), employeeDto.getUsername(), verifyCode);
		session.setAttribute(SessionConstant.CURRENT_OTP, verifyCode);
		VerifyCodeManager verifyCodeManager = new VerifyCodeManager();
		verifyCodeManager.scheduleVerificationCleanup(SessionConstant.OTP_EXPIRE_TIME * 1000, employeeDto.getUsername(), loginRepository);
		loginRepository.insert(employeeDto.getUsername(), hashPassword,employeeDto.getEmail(),verifyCode, 2L);
		return new ResponseObject(HttpStatus.OK, "Employee and Login was created successfully", verifyCode);
	}

}
