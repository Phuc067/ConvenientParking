package com.parking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.parking.model.IdRequest;
import com.parking.model.ResponseObject;
import com.parking.service.CheckOutService;

@RestController
@RequestMapping("/api/checkout")
public class CheckOutController {
	
	@Autowired
	private CheckOutService checkOutService;

	@PostMapping("/ready")
	public ResponseEntity<?> doPreCheckOut(@RequestBody IdRequest request )
	{
		ResponseObject responseObject = checkOutService.preCheckOut(request.getId());
		return ResponseEntity.status(responseObject.getStatus()).body(responseObject);
	}
	
	@PostMapping("")
	public ResponseEntity<?> doCheckOut(@RequestBody IdRequest request)
	{
		ResponseObject responseObject = checkOutService.checkOut(request.getId());
		return ResponseEntity.status(responseObject.getStatus()).body(responseObject);
	}
	
	
}
