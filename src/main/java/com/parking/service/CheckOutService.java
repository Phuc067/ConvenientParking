package com.parking.service;

import com.parking.model.ResponseObject;

public interface CheckOutService {
	ResponseObject preCheckOut(Long ticketId);

	ResponseObject checkOut(Long id);
}
