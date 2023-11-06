package com.parking.service;

import java.util.List;

import com.parking.entity.ParkingLot;

public interface ParkingLotSearchService {
	void setParkingLotSearchServiceImpl(List<ParkingLot> parkingLotDataList);
	void setNeuralNetwork();
	List<Integer> suggestParkingLots(double latitude, double longitude);
	int findNearestParkingLot(double latitude, double longitude);
}
