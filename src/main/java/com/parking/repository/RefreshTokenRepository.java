package com.parking.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.parking.entity.Login;
import com.parking.entity.RefreshToken;

public interface RefreshTokenRepository  extends JpaRepository<RefreshToken, Long>{
	RefreshToken findByToken(String token);
	
	@Query(name = "SELECT * FROM RefreshToken WHERE loginId = :#{#login.id}", nativeQuery =  true)
	Optional<RefreshToken> findByLogin(@Param("login") Login login);

}
