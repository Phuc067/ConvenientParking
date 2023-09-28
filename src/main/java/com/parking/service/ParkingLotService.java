package com.parking.service;

import java.util.List;

import com.parking.entity.ParkingLot;

public interface ParkingLotService {
	List<ParkingLot> getAllParkingLot();
	void add(ParkingLot parkingLot);
	Long getMaxId();
}
