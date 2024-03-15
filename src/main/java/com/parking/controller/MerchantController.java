package com.parking.controller;

import java.util.List;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.parking.dto.merchant.MerchantRequest;
import com.parking.dto.merchant.MerchantSearchParkingLotRequest;
import com.parking.dto.merchant.ReportRequest;
import com.parking.entity.Merchant;
import com.parking.entity.ParkingLot;
import com.parking.model.ResponseObject;
import com.parking.service.MerchantService;
import com.parking.service.ParkingLotService;

@RestController
@RequestMapping(value = "/api/mer-chants")
public class MerchantController {
	@Autowired
	private MerchantService service;
	
	@Autowired
	private ParkingLotService parkingLotService;

	@GetMapping("")
	public ResponseEntity<?> doGetAllMerchant() {
		List<Merchant> merchants = service.getAll();
		if (ObjectUtils.isEmpty(merchants)) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
		return ResponseEntity.status(HttpStatus.OK).body(merchants);
	}

	@PostMapping("")
	public ResponseEntity<?> doInsertMerchant(@RequestBody MerchantRequest merchant) {
		ResponseObject responseObject = service.insert(merchant);
		return ResponseEntity.status(responseObject.getStatus()).body(responseObject);
	}
	
	@PutMapping("")
	public ResponseEntity<?> doEditMerchant(@RequestBody Merchant merchant) {
		ResponseObject responseObject = service.edit(merchant);
		return ResponseEntity.status(responseObject.getStatus()).body(responseObject);
	}

	@GetMapping("/{username}")
	public ResponseEntity<?> doGetInformation(@PathVariable(name = "username") String username) {
		ResponseObject responseObject = service.getByUsername(username);
		return ResponseEntity.status(responseObject.getStatus()).body(responseObject);
	}

	@PostMapping("/reports")
	public ResponseEntity<?> doGetReport(@RequestBody ReportRequest report)
	{
		ResponseObject responseObject = service.getReportInYear(report.getParkingLotId(), report.getYear());
		return ResponseEntity.status(responseObject.getStatus()).body(responseObject);
	}
	
	@PostMapping("/{id}/parking-lots")
	public ResponseEntity<?> doGetParkingLotByMerChantId(@PathVariable(name = "id") Long id)
	{
		List<ParkingLot> parkingLots = parkingLotService.getParkingLotByMerchantId(id);
		return ResponseEntity.ok(parkingLots);
	}
	
	@PostMapping(value = "/search-parking-lots")
	public ResponseEntity<?> doSearchParkingByMerchantId(@RequestBody MerchantSearchParkingLotRequest request)
	{
		ResponseObject responseObject = parkingLotService.search(request);
		return ResponseEntity.status(responseObject.getStatus()).body(responseObject);
	}
}
