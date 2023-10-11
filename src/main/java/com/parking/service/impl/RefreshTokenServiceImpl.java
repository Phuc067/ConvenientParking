package com.parking.service.impl;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.parking.constant.RoleConstant;
import com.parking.constant.SessionConstant;
import com.parking.dto.RefreshTokenResponse;
import com.parking.entity.Login;
import com.parking.entity.RefreshToken;
import com.parking.model.ResponseObject;
import com.parking.model.RoleMap;
import com.parking.repository.LoginRepository;
import com.parking.repository.RefreshTokenRepository;
import com.parking.service.JwtService;
import com.parking.service.RefreshTokenService;

@Service
public class RefreshTokenServiceImpl  implements RefreshTokenService{
	@Autowired
	private RefreshTokenRepository refreshTokenRepository;
	
	@Autowired
	private LoginRepository loginRepository;
	
	@Autowired
	private JwtService jwtService;
	
	@Override
	public RefreshToken createRefreshToken(String username)
	{
		Login login = loginRepository.findByUsername(username);
		Optional<RefreshToken> refreshTokenDB = refreshTokenRepository.findByLogin(login);
		RefreshToken refreshToken = refreshTokenDB.isPresent()? refreshTokenDB.get() : new RefreshToken();
		if(refreshTokenDB.isPresent())
		{
			if(isExpired(refreshToken))
			{
				refreshTokenRepository.delete(refreshToken);
				RefreshToken newRefreshToken = new RefreshToken();
				newRefreshToken.setLogin(login);
				newRefreshToken.setExpriryDate(Instant.now().plusMillis(1000*60 *60));
				newRefreshToken.setToken(UUID.randomUUID().toString());
				return refreshTokenRepository.save(newRefreshToken);
			}
			else return refreshToken;
		}
		else {
			RefreshToken newRefreshToken = new RefreshToken();
			newRefreshToken.setLogin(login);
			newRefreshToken.setExpriryDate(Instant.now().plusMillis(1000*60 * SessionConstant.REFRESH_TOKEN_EXPRIRY_TIME));
			newRefreshToken.setToken(UUID.randomUUID().toString());
			return refreshTokenRepository.save(newRefreshToken);
		}
	}

	@Override
	public ResponseObject genarateAccessToken(String request) {
		RefreshToken refreshToken = refreshTokenRepository.findByToken(request);
		if(ObjectUtils.isEmpty(refreshToken) )
		{
			return new ResponseObject(HttpStatus.BAD_REQUEST, "Your refresh token does not exist", null);
		}
		if(isExpired(refreshToken))
		{
			refreshTokenRepository.delete(refreshToken);
			return new ResponseObject(HttpStatus.BAD_REQUEST, "Your refresh token was expired", null);
		}
		String accessToken = jwtService.generateToken(refreshToken.getLogin());
		return new ResponseObject(HttpStatus.OK,"Get access token successfully", new RefreshTokenResponse(accessToken));
	}
	
	public Boolean isExpired(RefreshToken refreshToken)
	{
		return refreshToken.getExpriryDate().compareTo(Instant.now()) < 0 ? true : false;
	}
}
