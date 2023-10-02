package com.parking.service;

import java.util.List;

import com.parking.dto.ParkingLotDto;
import com.parking.entity.ParkingLot;

public interface ParkingLotService {
	List<ParkingLot> getAllParkingLot();
	void add(ParkingLotDto parkingLotDto);
	Long getMaxId();
	List<ParkingLot> getParkingLotByMerchantId(Long id);
}
