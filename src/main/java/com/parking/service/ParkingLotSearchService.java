package com.parking.service;

public interface ParkingLotSearchService {
	int suggestParkingLot(double latitude, double longitude, double customerRating, boolean hasRoof, double cleanliness, double area);
}
