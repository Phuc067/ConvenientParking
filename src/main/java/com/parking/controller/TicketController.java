package com.parking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.parking.model.IdRequest;
import com.parking.model.ResponseObject;
import com.parking.service.TicketService;

@RestController
@RequestMapping("/api/ticket")
public class TicketController {
	
	@Autowired
	private TicketService ticketService;
	
	@PostMapping("/user")
	public ResponseEntity<?> doGetTicketUnPaidTicket(@RequestBody IdRequest request)
	{
		ResponseObject responseObject = ticketService.getUnpaidTicket(request.getId());
		return ResponseEntity.status(responseObject.getStatus()).body(responseObject);
	}
}
