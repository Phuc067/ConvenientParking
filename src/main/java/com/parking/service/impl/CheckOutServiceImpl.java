package com.parking.service.impl;

import java.sql.Timestamp;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.parking.constant.TransactionConstant;
import com.parking.dto.checkInOut.CheckInData;
import com.parking.dto.checkInOut.CheckOutData;
import com.parking.entity.PriceTicket;
import com.parking.entity.Ticket;
import com.parking.entity.Transaction;
import com.parking.model.ResponseObject;
import com.parking.repository.ParkingLotRepository;
import com.parking.repository.PriceTicketRepository;
import com.parking.repository.TicketRepository;
import com.parking.repository.TransactionRepository;
import com.parking.service.CheckOutService;
import com.parking.service.SocketService;
import com.parking.service.TicketService;
import com.parking.utils.TimeUtils;

@Service
public class CheckOutServiceImpl implements CheckOutService{
	
	@Autowired
	private TicketRepository ticketRepository;
	
	@Autowired
	private PriceTicketRepository priceTicketRepository;

	@Autowired
	private TicketService ticketService;
	
	@Autowired
	private ParkingLotRepository parkingLotRepository;
	
	@Autowired
	private TransactionRepository transactionRepository;
	
	@Autowired
	private SocketService socketService;
	
	@Override
	public ResponseObject preCheckOut(Long ticketId) {
		Ticket ticket = ticketRepository.findByid(ticketId);
		if(ObjectUtils.isEmpty(ticket))
		{
			return new ResponseObject(HttpStatus.NOT_FOUND, "Không tìm thấy vé", null);
		}
		if(ObjectUtils.isNotEmpty(ticket.getCheckOutTime()))
		{
			return new ResponseObject(HttpStatus.ALREADY_REPORTED, "Vé đã checkout rồi", null);
		}
		Timestamp timestamp = TimeUtils.getGMT_7( ticket.getCheckInTime());
		
		Long price  = ticketService.getPrice(timestamp, ticket.getParkingLot().getId(), ticket.getVehicleType().getId());
		CheckOutData checkOutData  =new CheckOutData(ticket.getLicensePlate(), price);
		socketService.sendPaymentRequest(ticket.getUser().getId(), price);
		return new ResponseObject(HttpStatus.OK, "Xác minh thông tin thành công", checkOutData);
	}

	@Override
	@Transactional
	public ResponseObject checkOut(Long id) {
		Ticket ticket = ticketRepository.findByid(id);
		if(ObjectUtils.isEmpty(ticket))
		{
			return new ResponseObject(HttpStatus.NOT_FOUND, "Không tìm thấy vé", null);
		}
	
		Transaction transaction = new Transaction(TransactionConstant.PAID,ticket, Timestamp.from(TimeUtils.now()), "Đã thanh toán");
		transactionRepository.save(transaction);
		parkingLotRepository.decreaseNumberSlotRemainingBy1(ticket.getParkingLot().getId());
		ticket.setCheckOutTime(Timestamp.from(TimeUtils.now()));
		ticketRepository.save(ticket);
		socketService.sendCheckOutSucessfull(ticket.getUser().getId());
		return new ResponseObject(HttpStatus.OK, "Checkout thành công", null);
	}
	
	
}
