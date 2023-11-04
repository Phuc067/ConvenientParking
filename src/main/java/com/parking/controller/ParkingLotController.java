package com.parking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.parking.dto.merchant.MerchantSearchParkingLotRequest;
import com.parking.dto.parkinglot.ParkingLotEdit;
import com.parking.dto.parkinglot.ParkingLotRequest;
import com.parking.dto.parkinglot.ParkingLotSearch;
import com.parking.entity.ParkingLot;
import com.parking.model.IdRequest;
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
	
	@PostMapping(value = "/edit")
	public ResponseEntity<?> doEditParkingLot(@RequestBody ParkingLotEdit parkingLot)
	{
		ResponseObject responseObject = parkingLotService.edit(parkingLot);
		return ResponseEntity.status(responseObject.getStatus()).body(responseObject);
	}
	
	@GetMapping(value = "/search")
	public ResponseEntity<ResponseObject> doSearchParkingLotByKeyWord(@RequestParam String keyword)
	{
		ResponseObject responseObject = parkingLotService.search(keyword);
		return ResponseEntity.status(responseObject.getStatus()).body(responseObject);
	}
	
	@PostMapping(value = "")
	public ResponseEntity<?> doGetParkingLotById(@RequestBody IdRequest request)
	{
		ResponseObject responseObject = parkingLotService.getById(request.getId());
		return ResponseEntity.status(responseObject.getStatus()).body(responseObject);
	}
	
	@PostMapping("/delete")
	public ResponseEntity<?> doDeleteParkingLotById(@RequestBody IdRequest request)
	{
		ResponseObject responseObject = parkingLotService.deleteById(request.getId());
		return ResponseEntity.status(responseObject.getStatus()).body(responseObject);
	}
	
	@PostMapping(value = "/search-by-merchant")
	public ResponseEntity<?> doSearchParkingByMerchantId(@RequestBody MerchantSearchParkingLotRequest request)
	{
		ResponseObject responseObject = parkingLotService.search(request);
		return ResponseEntity.status(responseObject.getStatus()).body(responseObject);
	}
	
	@PostMapping(value = "/vehicle-type")
	public ResponseEntity<?> doGetVehicleType(@RequestBody IdRequest request)
	{
		ResponseObject responseObject = parkingLotService.getVehicleType(request.getId());
		return ResponseEntity.status(responseObject.getStatus()).body(responseObject);
	}
	

}
