package com.parking.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.parking.constant.WebSocketConstant;
import com.parking.dto.checkInOut.CheckInData;
import com.parking.dto.checkInOut.CheckInInformation;
import com.parking.model.PreCheckOutData;
import com.parking.model.SocketLicensePlateData;
import com.parking.model.SocketMessage;
import com.parking.repository.PendingTicketRepository;
import com.parking.model.CheckInMessageData;
import com.parking.service.SocketService;



@Service
public class SocketServiceImpl implements SocketService {

	@Autowired
	private SimpMessagingTemplate template;

	@Autowired
	private PendingTicketRepository pendingTicketRepository;

	@Override
	public boolean sendToParkingLot(Long parkingLotID, CheckInMessageData checkInMessageData) {
		template.convertAndSend("/parkinglot/" + parkingLotID , checkInMessageData);
		return true;
	}
	
	@Override
	public boolean sendToUser(Long userId, SocketMessage socketMessage) {
		template.convertAndSend("/user/" + userId, socketMessage);
		return true;
	}
	
	@Override
	public boolean requestToEnterLicensePlate(CheckInData checkInData) {
		CheckInMessageData socketMessageData = new CheckInMessageData(WebSocketConstant.REQUEST_LICIENSE_PLATE, "“enter license plate and vehicle type”",
				checkInData);
		return sendToParkingLot(checkInData.getParkingLotId(), socketMessageData);
	}

	@Override
	public boolean sendCheckInSuccessful(Long parkingLotID, Long userId) {
		CheckInMessageData socketMessageData = new CheckInMessageData(WebSocketConstant.CHECKIN_SUCCESSFUL, "checkin success", null);
		
		return  sendToParkingLot(parkingLotID, socketMessageData);
	}

	@Override
	public boolean sendCheckInFail(Long parkingLotID, Long userId) {
		CheckInMessageData socketMessageData = new CheckInMessageData(WebSocketConstant.CHECKIN_FAILED, "checkin fail", null);
		return  sendToParkingLot(parkingLotID, socketMessageData);
	}

	@Override
	public void updateCheckInInformation(CheckInInformation checkInInformation) {
		if(pendingTicketRepository.isPendingTicket(checkInInformation.getCheckInData()))
		{
			pendingTicketRepository.setPendingTicketInformation(checkInInformation.getCheckInData(), checkInInformation.getVehicleData());
		}
	}


	@Override
	public boolean sendPaymentRequest(Long userId, Long price) {
		SocketMessage socketMessage = new SocketMessage(WebSocketConstant.PAYMENT_REQUEST, "Hãy tiến hành thanh toán số tiền :" + price); 
		return sendToUser(userId, socketMessage);
	}

	@Override
	public boolean sendCheckOutSucessfull(Long userId) {
		SocketMessage socketMessage = new SocketMessage(WebSocketConstant.CHECKOUT_SUCCESSFUL, "Thanh toán thành công"); 
		return sendToUser(userId, socketMessage);
	}

	@Override
	public boolean sendCheckOutFail(Long userId) {
		SocketMessage socketMessage = new SocketMessage(WebSocketConstant.CHECKOUT_FAILED, "Checkout thất bại"); 
		return sendToUser(userId, socketMessage);
	}


	

}
