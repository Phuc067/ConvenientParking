package com.parking.service;

import java.sql.Timestamp;

import com.parking.model.ResponseObject;

public interface TicketService {
	ResponseObject getUnpaidTicket(Long userId);
	Long getPrice(Timestamp checInTime, Long parkingLotId, Long vehicleTypeId);
}
