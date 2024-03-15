package com.parking.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.parking.dto.merchant.MerchantSearchParkingLotRequest;
import com.parking.dto.parkingLotImage.ParkingLotImageRequest;
import com.parking.dto.parkingLotImage.parkingLotImageEditRequest;
import com.parking.dto.parkinglot.ParkingLotEdit;
import com.parking.dto.parkinglot.ParkingLotRequest;
import com.parking.dto.parkinglot.ParkingLotSearch;
import com.parking.dto.priceTicket.PriceTicketRequest;
import com.parking.entity.ParkingLot;
import com.parking.model.ResponseObject;
import com.parking.service.ParkingLotImageService;
import com.parking.service.ParkingLotService;
import com.parking.service.PriceTicketService;

@RestController
@CrossOrigin
@RequestMapping(value = "/api/parking-lots")
public class ParkingLotController {

	@Autowired
	private ParkingLotService parkingLotService;
	
	@Autowired
	private ParkingLotImageService parkingLotImageService;
	
	@Autowired
	private PriceTicketService priceTicketService;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<?> doGetParkingLotById(@PathVariable(name = "id")Long id)
	{
		ResponseObject responseObject = parkingLotService.getById(id);
		return ResponseEntity.status(responseObject.getStatus()).body(responseObject);
	}
	
	@PostMapping("")
	public ResponseEntity<ResponseObject> doAddParkingLot(@RequestBody ParkingLotRequest parkingLotDto) {
		ResponseObject responseObject = parkingLotService.add(parkingLotDto);
		return ResponseEntity.status(responseObject.getStatus()).body(responseObject);
	}
	
	@PutMapping("")
	public ResponseEntity<?> doEditParkingLot(@RequestBody ParkingLotEdit parkingLot)
	{
		ResponseObject responseObject = parkingLotService.edit(parkingLot);
		return ResponseEntity.status(responseObject.getStatus()).body(responseObject);
	}
	
	@PostMapping(value = "/search")
	public ResponseEntity<ResponseObject> doSearchParkingLotByKeyWord(@RequestBody ParkingLotSearch request)
	{
		ResponseObject responseObject = parkingLotService.search(request);
		return ResponseEntity.status(responseObject.getStatus()).body(responseObject);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> doDeleteParkingLotById(@RequestParam(name = "id") Long id)
	{
		ResponseObject responseObject = parkingLotService.deleteById(id);
		return ResponseEntity.status(responseObject.getStatus()).body(responseObject);
	}
	
	@PostMapping(value = "/{id}/vehicle-types")
	public ResponseEntity<?> doGetVehicleType(@PathVariable(name = "id") Long id)
	{
		ResponseObject responseObject = parkingLotService.getVehicleType(id);
		return ResponseEntity.status(responseObject.getStatus()).body(responseObject);
	}
	
	@GetMapping("/{id}/images")
	public ResponseEntity<?> doDownLoadFile(@RequestParam("id") Long id) {

		ResponseObject responseObject = parkingLotImageService.download(id);
		return ResponseEntity.status(responseObject.getStatus()).body(responseObject);
	}
	
	@PostMapping("/{id}/images")
	public ResponseEntity<?> doUploadFile(@PathVariable(name = "id") Long id, String file) throws IOException {
		ResponseObject responseObject= parkingLotImageService.uploadImageToDB(id, file);
		return ResponseEntity.status(responseObject.getStatus()).body(responseObject);
	}
	

	@GetMapping("/{id}/price-tickets")
	public ResponseEntity<?> doGetPriceTicketOfAParkingLot(@PathVariable(name ="id") Long id){
		ResponseObject responseObject = priceTicketService.getByParkingLotId(id);
		return ResponseEntity.status(responseObject.getStatus()).body(responseObject);
	}
	
	@PostMapping("/{id}/price-tickets")
	public ResponseEntity<?> doAddpriceTicket(@PathVariable(name = "id") Long id, @RequestBody PriceTicketRequest request)
	{
		ResponseObject responseObject = priceTicketService.insert(id, request);
		return ResponseEntity.status(responseObject.getStatus()).body(responseObject);
	}

}
