package com.parking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.parking.entity.ParkingLotImage;

@Repository
public interface ParkingLotImageRepository extends JpaRepository<ParkingLotImage, Long> {

//	@Modifying(clearAutomatically = true)
//	@Query(value = "INSERT INTO ParkingLotImages(parkingLotId, url) values( ?1, ?2)", nativeQuery = true)
//	void insert(Long parkingLotId, String url);
	@Modifying(clearAutomatically = true)
	@Query(value = "INSERT INTO ParkingLotImages(parkingLotId, data) VALUES( ?1, ?2)", nativeQuery = true)
	void insert(Long parkingLotId, byte []image);
	
	List<ParkingLotImage> findByParkingLotId(Long parkingLotId);
	
	boolean existsByParkingLotIdAndData(long parkingLotId, byte data[]);

	@Modifying(clearAutomatically = true)
	@Query(value = "UPDATE ParkingLotImages SET data = ?2 WHERE id =?1", nativeQuery = true)
	void update(Long id, byte[] data);

}
