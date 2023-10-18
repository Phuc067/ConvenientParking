package com.parking.service;

import com.parking.entity.Login;
import com.parking.entity.RefreshToken;
import com.parking.model.ResponseObject;

public interface RefreshTokenService {
	RefreshToken createRefreshToken(Login login);

	ResponseObject genarateAccessToken(String request);
	
}
