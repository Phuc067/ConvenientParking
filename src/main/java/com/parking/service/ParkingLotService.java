package com.parking.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.parking.dto.merchant.MerchantSearchParkingLotRequest;
import com.parking.dto.parkinglot.ParkingLotRequest;
import com.parking.entity.ParkingLot;
import com.parking.model.ResponseObject;

public interface ParkingLotService {
	List<ParkingLot> getAllParkingLot();
	ResponseObject add(ParkingLotRequest parkingLotDto);
	Long getMaxId();
	List<ParkingLot> getParkingLotByMerchantId(Long id);
	ResponseObject search(String keyword);
	ResponseObject getById(Long id);
	ResponseObject search(MerchantSearchParkingLotRequest request);
	ResponseObject edit(ParkingLotRequest parkingLotRequest);
}
