package com.parking.repository;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.parking.entity.PriceTicket;


@Repository
public interface PriceTicketRepository extends JpaRepository<PriceTicket, Long>{
	PriceTicket findByParkingLotIdAndVehicleTypeIdAndEffectTimeLessThanEqualAndExpiredTimeGreaterThanEqual(Long parkinglotId, Long vehicleTypeId, Timestamp time, Timestamp time2);

	@Modifying(clearAutomatically = true)
	@Query(value = "{CALL sp_add_price_ticket(?1, ?2, ?3)}", nativeQuery = true)
	void insert(Long parkingLotId, Long vehicleTypeId, Long price);

	@Query(value = "SELECT * FROM pricetickets WHERE parkingLotId=?1 AND vehicleTypeId =?2 AND expiredTime IS NULL", nativeQuery = true)
	PriceTicket findByParkingLotIdAndVehicleTypeId(Long parkingLotId, Long vehicleTypeId);

	
	@Query(value = "SELECT vehicleTypeId WHERE parkingLotId = ?1 AND epiredTime IS NULL", nativeQuery = true)
	List<Long> vehicleTypeId(Long parkingLotId);
	
}