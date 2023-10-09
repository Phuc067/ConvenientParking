package com.parking.service;

import com.parking.entity.RefreshToken;
import com.parking.model.ResponseObject;

public interface RefreshTokenService {
	RefreshToken createRefreshToken(String username);

	ResponseObject genarateAccessToken(String request);
	
}
