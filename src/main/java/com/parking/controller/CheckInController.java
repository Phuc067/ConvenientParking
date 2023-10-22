package com.parking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.parking.dto.CheckInData;
import com.parking.model.ResponseObject;
import com.parking.service.CheckInService;


@RestController
@RequestMapping("/api/checkin")
public class CheckInController {
	
	@Autowired
	private CheckInService checkInService;
	
	@PostMapping("")
	public ResponseEntity<?> doCheckIn(@RequestBody CheckInData data)
	{
		ResponseObject responseObject = checkInService.checkIn(data);
		return null;
	}
}
