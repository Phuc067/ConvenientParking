package com.parking.service.impl;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.parking.dto.ParkingLotRequest;
import com.parking.entity.ParkingLot;
import com.parking.model.ResponseObject;
import com.parking.repository.ParkingLotRepository;
import com.parking.service.ParkingLotService;

@Service
public class ParkingLotServiceImpl implements ParkingLotService{

	@Autowired
	private ParkingLotRepository parkingLotRepository;
	
	@Override
	public List<ParkingLot> getAllParkingLot() {
		
		return parkingLotRepository.findAll();
	}

	@Override
	@Transactional
	public ResponseObject add(ParkingLotRequest parkingLotDto) {
		
		if(parkingLotRepository.existsByLatAndLng(parkingLotDto.getLat(), parkingLotDto.getLng()))
		{
			return new ResponseObject(HttpStatus.FORBIDDEN,"It is not possible to create 2 parking lots at the same location", null);
		}
		try {
			parkingLotRepository.insert(parkingLotDto);
		} catch (Exception e) {
			return new ResponseObject(HttpStatus.BAD_REQUEST, e.getMessage(), null);
		}
		return  new ResponseObject(HttpStatus.ACCEPTED, "Parking lot was added successfully", null);
	}

	@Override
	public Long getMaxId() {
		// TODO Auto-generated method stub
		return parkingLotRepository.getMaxId();
	}

	@Override
	public List<ParkingLot> getParkingLotByMerchantId(Long id) {
		
		return parkingLotRepository.findByMerchantId(id);
	}

}
