package com.parking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.parking.entity.ParkingLot;
import com.parking.service.ParkingLotService;

@RestController
@RequestMapping(value = "/api/parkingLots")
public class ParkingLotController {

	@Autowired
	private ParkingLotService parkingLotService;
	
	@GetMapping(value = "")
	public ResponseEntity<?> doGetAllParkingLot()
	{
		List<ParkingLot> parkingLots = parkingLotService.getAllParkingLot();
		System.out.println(parkingLots);
		return ResponseEntity.ok(parkingLots);
	}
}
