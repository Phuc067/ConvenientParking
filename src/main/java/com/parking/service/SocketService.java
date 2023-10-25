package com.parking.service;

import com.parking.dto.checkInOut.CheckInData;
import com.parking.dto.checkInOut.CheckInInformation;
import com.parking.model.PreCheckOutData;


import com.parking.model.CheckInMessageData;

public interface SocketService {
	boolean sendToParkingLot(Long parkingLotID, CheckInMessageData socketMessageData);
    boolean RequestToEnterLicensePlate(CheckInData checkInData);
    boolean SendCheckInSuccessful(Long parkingLotID);
    boolean SendCheckInFail(Long parkingLotID);
    boolean SendLicensePlate(Long parkingLotID, PreCheckOutData checkOutData, String licensePlate);
	void updateCheckInInformation(CheckInInformation checkInInformation);
}
