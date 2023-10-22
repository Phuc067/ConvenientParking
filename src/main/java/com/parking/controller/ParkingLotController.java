package com.parking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.parking.dto.ParkingLotRequest;
import com.parking.entity.ParkingLot;
import com.parking.model.ResponseObject;
import com.parking.service.ParkingLotService;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/parkinglot")
public class ParkingLotController {

	@Autowired
	private ParkingLotService parkingLotService;
	
	

	@GetMapping(value = "")
	public ResponseEntity<?> doGetAllParkingLot(@RequestParam(name = "merchantId", defaultValue = "0") Long id) {
		List<ParkingLot> parkingLots;
		if (id!=0) {
			parkingLots = parkingLotService.getParkingLotByMerchantId(id);
			return ResponseEntity.ok(parkingLots);
		} else {
			parkingLots = parkingLotService.getAllParkingLot();
			return ResponseEntity.ok(parkingLots);
		}
	}
	
	@PostMapping(value = "/add")
	public ResponseEntity<ResponseObject> doAddParkingLot(@RequestBody ParkingLotRequest parkingLotDto) {
		ResponseObject responseObject = parkingLotService.add(parkingLotDto);
		return ResponseEntity.status(responseObject.getStatus()).body(responseObject);
	}
	
	@GetMapping(value = "/search")
	public ResponseEntity<ResponseObject> doSearchParkingLotByKeyWord(@RequestParam("keyword") String keyword)
	{
		ResponseObject responseObject = parkingLotService.search(keyword);
		return ResponseEntity.status(responseObject.getStatus()).body(responseObject);
	}

}
