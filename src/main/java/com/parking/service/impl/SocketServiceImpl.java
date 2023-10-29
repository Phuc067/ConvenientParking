package com.parking.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.parking.dto.checkInOut.CheckInData;
import com.parking.dto.checkInOut.CheckInInformation;
import com.parking.model.PreCheckOutData;
import com.parking.model.SocketLicensePlateData;
import com.parking.repository.PendingTicketRepository;
import com.parking.model.CheckInMessageData;
import com.parking.service.SocketService;



@Service
public class SocketServiceImpl implements SocketService {

	private final SimpMessagingTemplate template = null;

	@Autowired
	private PendingTicketRepository pendingTicketRepository;

	@Override
	public boolean sendToParkingLot(Long parkingLotID, CheckInMessageData checkInMessageData) {
		template.convertAndSend("/parkinglot/" + parkingLotID , checkInMessageData);
		return true;
	}

	@Override
	public boolean RequestToEnterLicensePlate(CheckInData checkInData) {
		CheckInMessageData socketMessageData = new CheckInMessageData(-1, "“enter license plate and vehicle type”",
				checkInData);
		return sendToParkingLot(checkInData.getParkingLotId(), socketMessageData);
	}

	@Override
	public boolean SendCheckInSuccessful(Long parkingLotID) {
		CheckInMessageData socketMessageData = new CheckInMessageData(0, "checkin success", null);
		return sendToParkingLot(parkingLotID, socketMessageData);
	}

	@Override
	public boolean SendCheckInFail(Long parkingLotID) {
		CheckInMessageData socketMessageData = new CheckInMessageData(1, "checkin fail", null);
		return sendToParkingLot(parkingLotID, socketMessageData);
	}

	@Override
	public boolean SendLicensePlate(Long parkingLotID, PreCheckOutData checkOutData, String licensePlate) {
		SocketLicensePlateData socketLicensePlateData = new SocketLicensePlateData(2, "get checkout license plate",
				checkOutData, licensePlate);
		template.convertAndSend("/user/" + parkingLotID + "/parkinglot", socketLicensePlateData);
		return true;
	}

	@Override
	public void updateCheckInInformation(CheckInInformation checkInInformation) {
		if(pendingTicketRepository.isPendingTicket(checkInInformation.getCheckInData()))
		{
			pendingTicketRepository.setPendingTicketInformation(checkInInformation.getCheckInData(), checkInInformation.getVehicleData());
		}
	}

}
