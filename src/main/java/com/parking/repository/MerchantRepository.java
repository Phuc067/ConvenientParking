package com.parking.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.parking.entity.Merchant;

@Repository
public interface MerchantRepository  extends JpaRepository<Merchant, Long>{

	List<Merchant> findAll();
//    Optional<Merchant> findById(Long id);
}
