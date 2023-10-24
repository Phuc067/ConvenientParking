package com.parking.service;

import com.parking.dto.checkInOut.CheckInData;
import com.parking.model.ResponseObject;

public interface CheckInService {
	ResponseObject checkIn(CheckInData data);
}
