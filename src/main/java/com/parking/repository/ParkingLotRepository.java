package com.parking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.parking.dto.merchant.MerchantSearchParkingLotRequest;
import com.parking.dto.parkinglot.ParkingLotEdit;
import com.parking.dto.parkinglot.ParkingLotRequest;
import com.parking.dto.parkinglot.ParkingLotSearch;
import com.parking.entity.ParkingLot;

@Repository
public interface ParkingLotRepository extends JpaRepository<ParkingLot, Long>{
	List<ParkingLot> findAll();
	
	@Query(value = "SELECT MAX(ID) FROM PARKINGLOTS", nativeQuery = true)
	Long getMaxId();
	
	List<ParkingLot> findByMerchantId(Long id);

	@Modifying(clearAutomatically = true)
	@Query(value = "INSERT INTO ParkingLots(parkingLotName, numberSlot, numberSlotRemaining, status, merchantId, lat, lng, timeOpen, timeClose, city, district, ward, street, number) VALUES(:#{#parkingLot.parkingLotName}, :#{#parkingLot.numberSlot}, :#{#parkingLot.numberSlot}, 1, :#{#parkingLot.merchantId}, :#{#parkingLot.lat}, :#{#parkingLot.lng}, :#{#parkingLot.timeOpen}, :#{#parkingLot.timeClose}, :#{#parkingLot.city}, :#{#parkingLot.district}, :#{#parkingLot.ward}, :#{#parkingLot.street}, :#{#parkingLot.number} )",nativeQuery = true)
	void insert(@Param("parkingLot") ParkingLotRequest parkingLotDto);
	
	Boolean existsByLatAndLng(Double lat, Double lng);
	
	Boolean existsByCityAndDistrictAndWardAndStreetAndNumber(String city, String district, String ward, String Street, String number);
	
	@Modifying(clearAutomatically = true)
	@Query(value = "SELECT * FROM ParkingLots WHERE   CONCAT(parkingLotName, ' ', number, ' ', street, ' ', ward, ' ', district, ' ', city) COLLATE Latin1_General_CI_AI LIKE %?1% OR "
			+ " CONCAT(parkingLotName, ' ', number, ' ', street, ' ', district, ' ', city) COLLATE Latin1_General_CI_AI LIKE %?1%",nativeQuery = true)
	List<ParkingLot> search(String keyword);

	@Modifying(clearAutomatically = true)
	@Query(value = "SELECT * FROM ParkingLots WHERE merchantId = :#{#request.id} AND ( CONCAT(parkingLotName, ' ', number, ' ', street, ' ', ward, ' ', district, ' ', city) COLLATE Latin1_General_CI_AI LIKE %:#{#request.keyword}% OR "+
	" CONCAT(parkingLotName, ' ', number, ' ', street, ' ', district, ' ', city) COLLATE Latin1_General_CI_AI LIKE %:#{#request.keyword}%)" , nativeQuery = true)
	List<ParkingLot> search(@Param("request") MerchantSearchParkingLotRequest request);


	@Modifying(clearAutomatically = true)
	@Query(value = "UPDATE ParkingLots SET parkingLotName = :#{#parkingLot.parkingLotName}, numberSlot = :#{#parkingLot.numberSlot}, lat = :#{#parkingLot.lat}, lng = :#{#parkingLot.lng}, timeOpen = :#{#parkingLot.timeOpen}, timeClose = :#{#parkingLot.timeClose}, city = :#{#parkingLot.city}, district = :#{#parkingLot.district}, ward = :#{#parkingLot.ward}, street = :#{#parkingLot.street}, number = :#{#parkingLot.number} WHERE id = :#{#parkingLot.id}",nativeQuery = true)
	void edit(@Param("parkingLot") ParkingLotEdit parkingLotEdit);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value = "UPDATE PARKINGLOTS SET numberSlotRemaining = numberSlotRemaining + 1 WHERE id = ?1 and numberSlotRemaining < numberSlot", nativeQuery = true)
	void increaseNumberSlotRemainingBy1(Long parkingLotId);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value = "UPDATE PARKINGLOTS SET numberSlotRemaining = numberSlotRemaining - 1 WHERE id = ?1 and numberSlotRemaining > 0" , nativeQuery = true)
	void decreaseNumberSlotRemainingBy1(Long parkingLotId);
}
