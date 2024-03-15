package com.parking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.parking.dto.checkInOut.CheckInData;
import com.parking.dto.checkInOut.CheckInInformation;
import com.parking.model.ResponseObject;
import com.parking.service.CheckInService;
import com.parking.service.CheckOutService;
import com.parking.service.TicketService;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {
	
	@Autowired
	private TicketService ticketService;
	
	@Autowired
	private CheckOutService checkOutService;
	
	@Autowired
	private CheckInService checkInService;
	
	@GetMapping("/users/{id}/unpaid")
	public ResponseEntity<?> doGetTicketUnPaidTicket(@PathVariable(name = "id") Long id)
	{
		ResponseObject responseObject = ticketService.getUnpaidTicket(id);
		return ResponseEntity.status(responseObject.getStatus()).body(responseObject);
	}
	
	@GetMapping("/users/{id}/history")
	public ResponseEntity<?> doGetHistoryTicket(@PathVariable(name = "id") Long id)
	{
		ResponseObject responseObject = ticketService.getHistoryTicketOfUser(id);
		return ResponseEntity.status(responseObject.getStatus()).body(responseObject);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> doPreCheckOut(@PathVariable(name = "id") Long id)
	{
		ResponseObject responseObject = checkOutService.preCheckOut(id);
		return ResponseEntity.status(responseObject.getStatus()).body(responseObject);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> doCheckOut(@PathVariable(name = "id") long id)
	{
		ResponseObject responseObject = checkOutService.checkOut(id);
		return ResponseEntity.status(responseObject.getStatus()).body(responseObject);
	}
	
	@PostMapping("")
	public ResponseEntity<?> doCheckIn(@RequestBody CheckInData data)
	{
		ResponseObject responseObject = checkInService.checkIn(data);
		return ResponseEntity.status(responseObject.getStatus()).body(responseObject);
	}
	
	@PostMapping("/license-plate")
	public ResponseEntity<?> doSubmit(@RequestBody CheckInInformation information)
	{
		ResponseObject responseObject = checkInService.submitLicensePlate(information);
		return ResponseEntity.status(responseObject.getStatus()).body(responseObject);
	}
	
}
