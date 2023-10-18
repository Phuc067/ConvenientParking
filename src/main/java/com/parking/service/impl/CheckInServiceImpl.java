package com.parking.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.parking.dto.CheckInData;
import com.parking.entity.ParkingLot;
import com.parking.entity.User;
import com.parking.model.ResponseObject;
import com.parking.repository.ParkingLotRepository;
import com.parking.repository.PendingTicketRepository;
import com.parking.repository.UserRepository;
import com.parking.service.CheckInService;

@Service
public class CheckInServiceImpl  implements CheckInService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ParkingLotRepository parkingLotRepository;
	
	@Autowired
	private PendingTicketRepository pendingTicketRepository;

	@Override
	public ResponseObject checkIn(CheckInData data) {
		
		Optional<User> user = userRepository.findById(data.getUserId());
		Optional<ParkingLot> parkingLot = parkingLotRepository.findById(data.getParkingLotId());
		if(user.isEmpty() && parkingLot.isEmpty())
		{
			return new ResponseObject(HttpStatus.NOT_FOUND, "thông tin người dùng hoặc bãi đỗ xe không hợp lệ.", null);
		}
		if(parkingLot.get().getNumberSlotRemaining()==0)
		{
			return new ResponseObject(HttpStatus.BAD_REQUEST, "Bãi đỗ xe đã hết chỗ.", null);
		}
		if (!pendingTicketRepository.addPendingTicket(data)) {
            return new ResponseObject(HttpStatus.FOUND, "Vé đã được tạo rồi.", null);
        }
		
		return null;
		
	}
	
	
	
	
}
