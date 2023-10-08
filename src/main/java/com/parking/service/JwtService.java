package com.parking.service;

import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;


import io.jsonwebtoken.Claims;

public interface JwtService {
	Boolean isTokenValid(String token, UserDetails userDetails);
	String generateToken(UserDetails userDetails);
	String extractUsername(String token);
	<T> T extractClaim(String token, Function<Claims,T > claimsResolver);
	String generateToken(Map<String, Object> extraClaims, UserDetails userDetails);
}
