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

import com.parking.dto.ParkingLotDto;
import com.parking.entity.ParkingLot;
import com.parking.model.ResponseObject;
import com.parking.service.ParkingLotService;

@RestController
@CrossOrigin
@RequestMapping(value = "/api")
public class ParkingLotController {

	@Autowired
	private ParkingLotService parkingLotService;

	@GetMapping(value = "/parkingLots")
	public ResponseEntity<?> doGetAllParkingLot(@RequestParam(name = "merchantId", defaultValue = "0") Long id) {
		List<ParkingLot> parkingLots;
		if (id!=0) {
			parkingLots = parkingLotService.getParkingLotByMerchantId(id);
			System.out.println(parkingLots);
			return ResponseEntity.ok(parkingLots);
		} else {
			parkingLots = parkingLotService.getAllParkingLot();
			System.out.println(parkingLots);
			return ResponseEntity.ok(parkingLots);
		}
	}

	@PostMapping(value = "/add/parkingLot")
	public ResponseEntity<ResponseObject> doAddParkingLot(@RequestBody ParkingLotDto parkingLotDto) {
		try {
			parkingLotService.add(parkingLotDto);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseObject(e.getMessage(), null));
		}
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(new ResponseObject("Parking lot was created successfully", null));
	}

}
