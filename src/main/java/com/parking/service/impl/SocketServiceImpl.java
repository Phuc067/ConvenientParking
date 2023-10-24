package com.parking.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.parking.dto.checkInOut.CheckInData;
import com.parking.model.PreCheckOutData;
import com.parking.model.SocketLicensePlateData;
import com.parking.model.SocketMessageData;
import com.parking.service.SocketService;


@Service
public class SocketServiceImpl  implements SocketService{

	 @Autowired
	    SimpMessagingTemplate template;

	    @Override
	    public boolean sendToMerchant(Long parkingLotID, SocketMessageData socketMessageData) {
	        template.convertAndSend("/user/" + parkingLotID + "/merchant" , socketMessageData);
	        return true;
	    }

	    @Override
	    public boolean RequestToEnterLicensePlate(CheckInData checkInData) {
	        SocketMessageData socketMessageData = new SocketMessageData(-1, "“enter license plate and vehicle type”", checkInData);
	        return sendToMerchant(checkInData.getParkingLotId(), socketMessageData);
	    }

	    @Override
	    public boolean SendCheckInSuccessful(Long parkingLotID) {
	        SocketMessageData socketMessageData = new SocketMessageData(0, "checkin success", null);
	        return sendToMerchant(parkingLotID, socketMessageData);
	    }

	    @Override
	    public boolean SendCheckInFail(Long parkingLotID) {
	        SocketMessageData socketMessageData = new SocketMessageData(1, "checkin fail", null);
	        return sendToMerchant(parkingLotID, socketMessageData);
	    }
	    
	    @Override
	    public boolean SendLicensePlate(Long parkingLotID, PreCheckOutData checkOutData, String licensePlate) {
	        SocketLicensePlateData socketLicensePlateData = new SocketLicensePlateData(2, "get checkout license plate", checkOutData, licensePlate);
	        template.convertAndSend("/user/" + parkingLotID + "/merchant" , socketLicensePlateData);
	        return true;
	    }

}
