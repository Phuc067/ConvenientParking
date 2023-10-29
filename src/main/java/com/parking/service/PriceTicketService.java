package com.parking.service;

import com.parking.dto.priceTicket.PriceTicketRequest;
import com.parking.model.ResponseObject;

public interface PriceTicketService {

	ResponseObject getByParkingLotId(Long parkingLotId);

	ResponseObject insert(PriceTicketRequest request);

}
