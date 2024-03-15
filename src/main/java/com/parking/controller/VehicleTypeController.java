package com.parking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.parking.model.ResponseObject;
import com.parking.service.VehicleTypeService;

@RestController
@RequestMapping(value = "/api/vehicle-types")
public class VehicleTypeController {
	
	
	@Autowired
	private VehicleTypeService vehicleTypeService;
	
	@GetMapping("")
	public ResponseEntity<?> doGetVehicleType()
	{
		ResponseObject responseObject = vehicleTypeService.getAll();
		return ResponseEntity.status(responseObject.getStatus()).body(responseObject);
	}
}
