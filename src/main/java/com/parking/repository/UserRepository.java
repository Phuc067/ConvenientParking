package com.parking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.parking.dto.user.UserInsert;
import com.parking.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	User findByLoginId(Long id);
	
	List<User> findAll();

	@Modifying(clearAutomatically = true)
	@Query(value = "INSERT INTO EndUsers(firstName, lastName, gender, phone, momoId, loginId) VALUES(:#{#user.firstName}, :#{#user.lastName}, :#{#user.gender}, :#{#user.phone}, :#{#user.momoId}, :#{#user.loginId})", nativeQuery = true)
	void insert(@Param("user") UserInsert user);
}
