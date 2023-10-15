package com.parking.service;

import javax.mail.MessagingException;

import com.parking.dto.ResetPasswordRequest;
import com.parking.model.ResponseObject;

public interface LoginService {
	ResponseObject getAll();
	ResponseObject forget(String username) throws MessagingException;
	ResponseObject resetPassword(ResetPasswordRequest request);
}
