package com.parking.service;

import javax.mail.MessagingException;

import com.parking.dto.LoginRequest;
import com.parking.dto.ResetPasswordRequest;
import com.parking.model.ResponseObject;

public interface LoginService {
	ResponseObject getAll();

	ResponseObject changePassword(LoginRequest loginRequest);
}
