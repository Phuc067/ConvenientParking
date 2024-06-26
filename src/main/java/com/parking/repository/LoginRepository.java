package com.parking.repository;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.parking.entity.Login;
import com.parking.entity.User;

@Repository
public interface LoginRepository extends JpaRepository<Login, Long> {

	Login findByUsername(String username);
	
	Login findByEmail(String email);
	
	@Query(value = "SELECT MAX(ID) FROM Logins", nativeQuery = true)
	Long getMaxId();
	
	@Modifying(clearAutomatically = true)
	@Query(value = "INSERT INTO logins(username, password, email, verificationCode, status, roleId) VALUES (?1, ?2, ?3, ?4, 0, ?5)", nativeQuery = true)
	Login insert(String username,String password,String email, String verificationCode ,Long roleId);
	
	@Modifying(clearAutomatically = true)
	@Transactional(value = TxType.REQUIRED)
	@Query(value = "UPDATE logins SET verificationCode = NULL WHERE username = ?1", nativeQuery = true)
	void removeVerificationCode(String username);
	
	@Modifying(clearAutomatically = true)
	@Query(value = "UPDATE logins SET verificationCode = ?2 WHERE username = ?1", nativeQuery = true)
	void setVerificationCode(String username, String verificationCode);
	
	@Modifying(clearAutomatically = true)
	@Query(value = "UPDATE logins SET status = 1 WHERE username = ?1", nativeQuery = true)
	void setStatus(String username);
	
	@Modifying(clearAutomatically = true)
	@Query(value = "UPDATE logins SET password = ?2 WHERE username = ?1", nativeQuery = true)
	void setPassword(String username, String password);

	User save(User user);
	
	
}
