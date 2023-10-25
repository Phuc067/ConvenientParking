package com.parking.repository;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.parking.dto.merchant.MerchantRequest;
import com.parking.entity.Merchant;

@Repository
public interface MerchantRepository  extends JpaRepository<Merchant, Long>{

	List<Merchant> findAll();
//    Optional<Merchant> findById(Long id);
	Merchant findByLoginId(Long id);
	
	@Modifying(clearAutomatically = true)
	@Query(value = "UPDATE merchants SET name = :#{#merchant.name}, represent = :#{#merchant.represent}, email = :#{#merchant.email}, phone = :#{#merchant.phone} WHERE id = :#{#merchant.id}", nativeQuery = true)
	void edit(@Param("merchant") Merchant merchant);
	
	Merchant findByLoginUsername(String username);
	
	@Modifying(clearAutomatically = true)
	@Query(value = "INSERT INTO Merchants(name, represent, phone, loginId) VALUES (:#{#merchant.name}, :#{#merchant.represent}, :#{#merchant.phone}, :#{#merchant.loginId})", nativeQuery = true)
	void insert(@Param("merchant") MerchantRequest merchant);
	
	
}
