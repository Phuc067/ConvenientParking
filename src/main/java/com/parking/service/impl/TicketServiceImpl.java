package com.parking.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.parking.dto.ticket.HistoryTicket;
import com.parking.dto.ticket.TicketResponse;
import com.parking.entity.PriceTicket;
import com.parking.entity.Ticket;
import com.parking.model.ResponseObject;
import com.parking.repository.PriceTicketRepository;
import com.parking.repository.SpRepository;
import com.parking.repository.TicketRepository;
import com.parking.service.TicketService;
import com.parking.utils.TimeUtils;

@Service
public class TicketServiceImpl implements TicketService{

	@Autowired
	private TicketRepository ticketRepository;
	
	@Autowired
	private PriceTicketRepository priceTicketRepository;
	
	@Autowired
	private SpRepository spRepository;
	
	@Override
	public ResponseObject getUnpaidTicket(Long userId) {
		
		
		List<TicketResponse> tickets = spRepository.getUnpaidTicket(userId);
		for(TicketResponse ticket: tickets)
		{
			Long price = getPrice(ticket.getCheckInTime(), ticket.getParkingLotId(), ticket.getVehicleTypeId());
			ticket.setPrice(price);
		}
		return new ResponseObject(HttpStatus.OK, "Lấy thành công danh sách vé chưa thanh toán", tickets);
	}

	@Override
	public Long getPrice(Timestamp checInTime, Long parkingLotId, Long vehicleTypeId) {
		Double hours = TimeUtils.intervalToNow(checInTime);
		PriceTicket priceTicket = priceTicketRepository.findByParkingLotIdAndVehicleTypeId( parkingLotId,vehicleTypeId);
		Long days = (long) Math.ceil(hours/24);
		Long price = priceTicket.getPrice() * days;
		return price;
	}

	@Override
	public ResponseObject getHistoryTicketOfUser(long userId) {
		try {
			List<HistoryTicket> historyTickets = spRepository.getHistoryTicket(userId);
			return new ResponseObject(HttpStatus.OK, "Lấy lịch sử vé thành công", historyTickets);
		}
		catch (Exception e) {
			e.printStackTrace();
			return new ResponseObject(HttpStatus.OK, "Lấy lịch sử vé thành công", null);
		}
	}
	
	
	
}
