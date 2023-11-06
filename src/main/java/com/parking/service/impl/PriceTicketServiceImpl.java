package com.parking.service.impl;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.parking.dto.priceTicket.PriceTicketRequest;
import com.parking.dto.priceTicket.PriceTicketResponse;
import com.parking.entity.PriceTicket;
import com.parking.model.ResponseObject;
import com.parking.repository.ParkingLotRepository;
import com.parking.repository.PriceTicketRepository;
import com.parking.repository.SpRepository;
import com.parking.repository.VehicleTypeRepository;
import com.parking.service.PriceTicketService;

@Service
public class PriceTicketServiceImpl implements PriceTicketService {

	@Autowired
	private SpRepository spRepository;

	@Autowired
	private PriceTicketRepository priceTicketRepository;
	
	@Override
	public ResponseObject getByParkingLotId(Long parkingLotId) {
		try {
			List<PriceTicketResponse> priceTickets = spRepository.getCurrentPriceTicket(parkingLotId);
			return new ResponseObject(HttpStatus.OK, "Lấy giá vé thành công", priceTickets);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseObject(HttpStatus.ACCEPTED, "Lấy giá vé thất bại", null);
		}
	}

	@Override
	@Transactional
	public ResponseObject insert(PriceTicketRequest request) {
		try {
			System.out.println(request.getParkingLotId() + " " + request.getVehicleTypeId()+" "+ request.getPrice());
			priceTicketRepository.insert(request.getParkingLotId(), request.getVehicleTypeId(),request.getPrice());
			return new ResponseObject(HttpStatus.OK, "Thêm giá vé thành công", null);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseObject(HttpStatus.ACCEPTED, "Thêm giá vé thất bại", null);
		}
	}
}
