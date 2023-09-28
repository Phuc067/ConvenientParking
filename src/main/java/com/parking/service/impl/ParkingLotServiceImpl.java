package com.parking.service.impl;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parking.dto.ParkingLotDto;
import com.parking.entity.ParkingLot;
import com.parking.repository.ParkingLotRepository;
import com.parking.service.ParkingLotService;

@Service
public class ParkingLotServiceImpl implements ParkingLotService{

	@Autowired
	private ParkingLotRepository repo;
	
	@Override
	public List<ParkingLot> getAllParkingLot() {
		
		return repo.findAll();
	}

	@Override
	public void add(ParkingLot parkingLot) {
		repo.save(parkingLot);
	}

	@Override
	public Long getMaxId() {
		// TODO Auto-generated method stub
		return repo.getMaxId();
	}

}
