package com.parking.repository;

import java.sql.Timestamp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.parking.entity.PriceTicket;


@Repository
public interface PriceTicketRepository extends JpaRepository<PriceTicket, Long>{
	PriceTicket findByParkingLotIdAndVehicleTypeIdAndEffectTimeLessThanEqualAndExpiredTimeGreaterThanEqual(Long parkinglotId, Long vehicleTypeId, Timestamp time, Timestamp time2);

	@Modifying(clearAutomatically = true)
	@Query(value = "UPDATE pricetickets SET expiredTime = ?2 WHERE id = ?1", nativeQuery = true)
	void updateExpiredTime( Long id, Timestamp now);


	@Modifying(clearAutomatically = true)
	@Query(value = "INSERT INTO pricetickets(parkingLotId, vehicleTypeId, effectTime, expiredTime, price) VALUES(?1, ?2, ?3, NULL,?4)", nativeQuery = true)
	void insert(Long parkingLotId, Long vehicleTypeId, Timestamp now, Long price);

	@Query(value = "SELECT * FROM pricetickets WHERE parkingLotId=?1 AND vehicleTypeId =?2 AND expiredTime IS NULL", nativeQuery = true)
	PriceTicket findByParkingLotIdAndVehicleTypeId(Long parkingLotId, Long vehicleTypeId);

	
}