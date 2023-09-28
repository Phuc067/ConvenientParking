package com.parking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.parking.dto.ParkingLotDto;
import com.parking.entity.Merchant;
import com.parking.entity.ParkingLot;
import com.parking.model.ResponseObject;
import com.parking.service.MerchantService;
import com.parking.service.ParkingLotService;

@RestController
@RequestMapping(value = "/api")
public class ParkingLotController {

	@Autowired
	private ParkingLotService parkingLotService;
	
	@Autowired 
	private MerchantService merchantService;
	
	@GetMapping(value = "/parkingLots")
	public ResponseEntity<?> doGetAllParkingLot()
	{
		List<ParkingLot> parkingLots = parkingLotService.getAllParkingLot();
		System.out.println(parkingLots);
		return ResponseEntity.ok(parkingLots);
	}
	@PostMapping(value = "/add/parkingLot")
	public ResponseEntity<ResponseObject> doAddParkingLot(@RequestBody ParkingLotDto parkingLotDto)
	{
		ParkingLot parkingLot = new ParkingLot();
		
		Long id = parkingLotService.getMaxId() + 1;
		System.out.println(id);
		parkingLot.setId(id);
		parkingLot.setParkingLotName(parkingLotDto.getParkingLotName());
		parkingLot.setNumberSlot(parkingLotDto.getNumberSlot());
		parkingLot.setNumberSlotRemaining(parkingLotDto.getNumberSlot());
		Merchant merchant = merchantService.getById(parkingLotDto.getMerchantId());
		parkingLot.setMerchant(merchant);
		parkingLot.setStatus(1l);
		parkingLot.setLat(parkingLotDto.getLat());
		parkingLot.setLng(parkingLotDto.getLng());
		parkingLot.setTimeClose(parkingLotDto.getTimeClose());
		parkingLot.setTimeOpen(parkingLotDto.getTimeOpen());
		parkingLot.setCity(parkingLotDto.getCity());
		parkingLot.setDistrict(parkingLotDto.getDistrict());
		parkingLot.setWard(parkingLotDto.getWard());
		parkingLot.setStreet(parkingLotDto.getStreet());
		parkingLot.setNumber(parkingLotDto.getNumber());
		
		try {
			parkingLotService.add(parkingLot);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseObject(e.getMessage(),null ));
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseObject("Parking lot was created successfully", null));
	}
	
}
