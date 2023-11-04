package com.parking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.parking.entity.VehicleType;

@Repository
public interface VehicleTypeRepository extends JpaRepository<VehicleType, Long>{
	
	List<VehicleType> findAll();
	
	
	@Query(value = "{CALL SP_GET_VEHICLE_TYPE_OF_PARKINGLOT(?1)}", nativeQuery = true)
	List<VehicleType> findByParkingLotId(Long parkingLotId);
}
