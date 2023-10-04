package com.parking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.parking.entity.Login;

@Repository
public interface LoginRepository extends JpaRepository<Login, Long> {

	Login findByUsername(String username);
	
	@Modifying(clearAutomatically = true)
	@Query(value = "INSERT INTO logins(username, password, status, roleId) values (?1, ?2, 0, ?3)", nativeQuery = true)
	void insert(String username,String password, Long roleId);
}
