package com.parking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.parking.entity.ParkingLot;

@Repository
public interface ParkingLotRepository extends JpaRepository<ParkingLot, Long>{
	List<ParkingLot> findAll();
	
	@Query(value = "SELECT MAX(ID) FROM PARKINGLOTS", nativeQuery = true)
	Long getMaxId();
	
	List<ParkingLot> findByMerchantId(Long id);
}
