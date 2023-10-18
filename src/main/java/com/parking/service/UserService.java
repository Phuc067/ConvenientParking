package com.parking.service;
import com.parking.model.ResponseObject;
public interface UserService {
	ResponseObject getByUsername(String username);
}
