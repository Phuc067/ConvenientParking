package com.parking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.parking.dto.priceTicket.PriceTicketRequest;
import com.parking.model.IdRequest;
import com.parking.model.ResponseObject;
import com.parking.service.PriceTicketService;

@RestController
@RequestMapping("/api/price-ticket")
public class PriceTicketController {
	
	@Autowired
	private PriceTicketService priceTicketService;
	
	@PostMapping
	public ResponseEntity<?> doGetPriceTicketOfAParkingLot(@RequestBody IdRequest request){
		ResponseObject responseObject = priceTicketService.getByParkingLotId(request.getId());
		return ResponseEntity.status(responseObject.getStatus()).body(responseObject);
	}
	
	@PostMapping("/add")
	public ResponseEntity<?> doAddpriceTicket(@RequestBody PriceTicketRequest request)
	{
		ResponseObject responseObject = priceTicketService.insert(request);
		return ResponseEntity.status(responseObject.getStatus()).body(responseObject);
	}

}