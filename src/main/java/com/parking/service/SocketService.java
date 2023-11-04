package com.parking.service;

import com.parking.dto.checkInOut.CheckInData;
import com.parking.dto.checkInOut.CheckInInformation;
import com.parking.model.PreCheckOutData;
import com.parking.model.SocketMessage;
import com.parking.model.CheckInMessageData;

public interface SocketService {
	boolean sendToParkingLot(Long parkingLotID, CheckInMessageData socketMessageData);
	boolean sendToUser(Long userId, SocketMessage socketMessage);
    boolean requestToEnterLicensePlate(CheckInData checkInData);
    boolean sendCheckInSuccessful(Long parkingLotID, Long userId);
    boolean sendCheckInFail(Long parkingLotID,Long userId);
    boolean sendPaymentRequest(Long userId, Long price);
    boolean sendCheckOutFail(Long userId);
    boolean sendCheckOutSucessfull(Long userId);
	void updateCheckInInformation(CheckInInformation checkInInformation);
}
