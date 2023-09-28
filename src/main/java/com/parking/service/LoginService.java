package com.parking.service;

import com.parking.dto.LoginDto;
import com.parking.entity.Login;

public interface LoginService {
	
	Object doLogin(LoginDto loginDto);
}
