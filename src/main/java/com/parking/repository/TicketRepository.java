package com.parking.repository;

import java.time.Instant;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.parking.dto.checkInOut.CheckInData;
import com.parking.entity.Ticket;
import com.parking.model.VehicleData;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
	
	Ticket findByid(Long id);
	
	@Query(value = "SELECT * FROM TICKETS WHERE parkingLotId = ?2 AND endUserId = ?1 AND licensePlate = ?3 AND checkoutTime is NULL", nativeQuery = true)
	Ticket findByUserIdAndParkingLotIdAndLicensePlate(Long userId, Long parkingLotId, String licensePlate);


	@Modifying(clearAutomatically = true)
	@Query(value = "INSERT INTO TICKETS(checkInTime, checkOutTime, licensePlate, vehicleTypeId, endUserId, parkingLotId) VALUES(?1, NULL, :#{#vehicle.licensePlate}, :#{#vehicle.vehicleTypeId}, :#{#data.userId}, :#{#data.parkingLotId})", nativeQuery = true)
	void insert(Instant now, @Param("vehicle") VehicleData vehicleData, @Param("data") CheckInData data);


	@Query(value = "SELECT * FROM TICKETS WHERE endUserId = ?1 AND checkOutTime IS NULL", nativeQuery = true)
	List<Ticket> findByUserIdAndUnPaid(Long userId);
	
}
