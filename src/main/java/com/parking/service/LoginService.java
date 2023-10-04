package com.parking.service;

import javax.servlet.http.HttpSession;

import com.parking.dto.LoginDto;
import com.parking.entity.Login;

public interface LoginService {
	
	Object doLogin(LoginDto loginDto);
	Object register(LoginDto loginDto);
}
