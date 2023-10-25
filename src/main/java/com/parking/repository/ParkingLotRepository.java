package com.parking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.parking.dto.merchant.MerchantSearchParkingLotRequest;
import com.parking.dto.parkinglot.ParkingLotEdit;
import com.parking.dto.parkinglot.ParkingLotRequest;
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
	
	@Modifying(clearAutomatically = true)
	@Query(value = "SELECT * FROM ParkingLots WHERE(parkingLotName + ' ' + number + ' ' +  street + ' ' + ward + ' ' + district + ' ' + city)  LIKE %?1%  or (parkingLotName + ' ' + number + ' ' +  street + ' '  + district + ' ' + city) LIKE %?1%" , nativeQuery = true)
	List<ParkingLot> search(String keyword);

	
	@Modifying(clearAutomatically = true)
	@Query(value = "SELECT * FROM ParkingLots WHERE merchantId = :#{#request.id} AND ( (parkingLotName + ' ' + number + ' ' +  street + ' ' + ward + ' ' + district + ' ' + city) LIKE %:#{#request.keyword}%  OR (parkingLotName + ' ' + number + ' ' +  street + ' '  + district + ' ' + city) LIKE %:#{#request.keyword}%) " , nativeQuery = true)
	List<ParkingLot> search(@Param("request") MerchantSearchParkingLotRequest request);


	@Modifying(clearAutomatically = true)
	@Query(value = "UPDATE ParkingLots SET parkingLotName = :#{#parkingLot.parkingLotName}, numberSlot = :#{#parkingLot.numberSlot}, lat = :#{#parkingLot.lat}, lng = :#{#parkingLot.lng}, timeOpen = :#{#parkingLot.timeOpen}, timeClose = :#{#parkingLot.timeClose}, city = :#{#parkingLot.city}, district = :#{#parkingLot.district}, ward = :#{#parkingLot.ward}, street = :#{#parkingLot.street}, number = :#{#parkingLot.number} WHERE id = :#{#parkingLot.id}",nativeQuery = true)
	void edit(@Param("parkingLot") ParkingLotEdit parkingLotEdit);

}
