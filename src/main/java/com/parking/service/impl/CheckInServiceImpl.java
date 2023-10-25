package com.parking.service.impl;

import java.time.Duration;
import java.time.Instant;
import java.util.Optional;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.parking.dto.checkInOut.CheckInData;
import com.parking.entity.ParkingLot;
import com.parking.entity.User;
import com.parking.model.ResponseObject;
import com.parking.model.VehicleData;
import com.parking.repository.ParkingLotRepository;
import com.parking.repository.PendingTicketRepository;
import com.parking.repository.UserRepository;
import com.parking.service.CheckInService;
import com.parking.service.SocketService;

@Service
public class CheckInServiceImpl  implements CheckInService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ParkingLotRepository parkingLotRepository;
	
	@Autowired
	private PendingTicketRepository pendingTicketRepository;
	
	@Autowired
	private SocketService socketService;

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
		
		socketService.RequestToEnterLicensePlate(data);
		VehicleData vehicleData = getVehicleData(data);
		if(ObjectUtils.isEmpty(vehicleData))
		{
			return new ResponseObject(HttpStatus.FOUND, "Chưa có thông tin xe", null);
		}
		return null;
		
	}

	private VehicleData getVehicleData(CheckInData data) {
		Instant begin = Instant.now();
		while(pendingTicketRepository.isPendingTicket(data))
		{
			Instant now = Instant.now();
			Duration duration = Duration.between(now, begin);
			if(duration.getSeconds()>60)
			{
				pendingTicketRepository.removePendingTicket(data);
				return null;
			}
			if(pendingTicketRepository.getPendingTicketInformation(data).getVehicleTypeId() != -1)
			{
				return pendingTicketRepository.getPendingTicketInformation(data);
			}
			
		}
		return null;
	}
	
}
