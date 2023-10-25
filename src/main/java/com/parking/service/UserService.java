package com.parking.service;
import com.parking.dto.user.UserInsert;
import com.parking.entity.User;
import com.parking.model.ResponseObject;
public interface UserService {
	ResponseObject getByUsername(String username);
	ResponseObject insert(UserInsert user);
}
