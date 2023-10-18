package com.parking.service.impl;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.parking.constant.RoleConstant;
import com.parking.constant.SessionConstant;
import com.parking.entity.Login;
import com.parking.model.RoleMap;
import com.parking.service.JwtService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtServiceImpl implements JwtService {

	private static final String SECRET_KEY = "1c048e54b2af90faec88a3df33c0b7a0bddd5c3e12b858ab16b619ec34353d7c";

	@Override
	public String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);
	}

	private Claims extractAllClaims(String token) {

		return Jwts
				.parserBuilder()
				.setSigningKey(getSignInKey())
				.build()
				.parseClaimsJws(token)
				.getBody();
	}

	private Key getSignInKey() {
		byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
		return Keys.hmacShaKeyFor(keyBytes);
	}

	@Override
	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}

	@Override
	public String generateToken(Map<String, Object> extraClaims, Login login) {
		return Jwts.builder()
				.setClaims(extraClaims)
				.setSubject(login.getUsername())
				.claim("role", RoleConstant.roleMap.get(login.getRole().getName()))
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * SessionConstant.ACCESS_TOKEN_EXPRIRY_TIME))
				.signWith(getSignInKey(), SignatureAlgorithm.HS256).compact();
	}

	@Override
	public String generateToken(Login login) {

		return generateToken(new HashMap<>(), login);
	}

	@Override
	public Boolean isTokenValid(String token, UserDetails userDetails) {
		final String username = extractUsername(token);
		return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
	}

	private Boolean isTokenExpired(String token) {
		
		return extractExpiration(token).before(new Date());
	}

	private Date extractExpiration(String token) {

		return extractClaim(token, Claims::getExpiration);
	}

}
