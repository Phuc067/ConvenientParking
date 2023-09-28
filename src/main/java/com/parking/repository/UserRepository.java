package com.parking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.parking.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	User findByLoginId(Long id);
}
