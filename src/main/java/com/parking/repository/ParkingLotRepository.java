package com.parking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.parking.dto.ParkingLotRequest;
import com.parking.entity.ParkingLot;

@Repository
public interface ParkingLotRepository extends JpaRepository<ParkingLot, Long>{
	List<ParkingLot> findAll();
	
	@Query(value = "SELECT MAX(ID) FROM PARKINGLOTS", nativeQuery = true)
	Long getMaxId();
	
	List<ParkingLot> findByMerchantId(Long id);

	@Modifying(clearAutomatically = true)
	@Query(value = "INSERT INTO ParkingLots(parkingLotName, numberSlot, numberSlotRemaining, status, merchantId, lat, lng, timeOpen, timeClose, city, district, ward, street, number) values(:#{#parkingLot.parkingLotName}, :#{#parkingLot.numberSlot}, :#{#parkingLot.numberSlot}, 1, :#{#parkingLot.merchantId}, :#{#parkingLot.lat}, :#{#parkingLot.lng}, :#{#parkingLot.timeOpen}, :#{#parkingLot.timeClose}, :#{#parkingLot.city}, :#{#parkingLot.district}, :#{#parkingLot.ward}, :#{#parkingLot.street}, :#{#parkingLot.number} )",nativeQuery = true)
	void insert(@Param("parkingLot") ParkingLotRequest parkingLotDto);
	
	Boolean existsByLatAndLng(Double lat, Double lng);
	
	@Modifying(clearAutomatically = true)
	@Query(value = "SELECT * FROM ParkingLots where (parkingLotName + ' ' + number + ' ' +  street + ' ' + ward + ' ' + district + ' ' + city) like %?1% " , nativeQuery = true)
	List<ParkingLot> search(String keyword);

}
