package com.parking.service;

import com.parking.dto.CheckInData;
import com.parking.model.PreCheckOutData;
import com.parking.model.SocketMessageData;

public interface SocketService {
	boolean sendToMerchant(Long parkingLotID, SocketMessageData socketMessageData);
    boolean RequestToEnterLicensePlate(CheckInData checkInData);
    boolean SendCheckInSuccessful(Long parkingLotID);
    boolean SendCheckInFail(Long parkingLotID);
    boolean SendLicensePlate(Long parkingLotID, PreCheckOutData checkOutData, String licensePlate);
}
