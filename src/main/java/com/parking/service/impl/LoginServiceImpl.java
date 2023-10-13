package com.parking.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.parking.entity.Login;
import com.parking.model.ResponseObject;
import com.parking.repository.LoginRepository;
import com.parking.service.LoginService;

@Service
public class LoginServiceImpl  implements LoginService{
	
	@Autowired
	private LoginRepository loginRepository;
	
	@Override
	public ResponseObject getAll()
	{
		List<Login> logins = loginRepository.findAll();
		if(logins.size() ==0)
		{
			return new ResponseObject(HttpStatus.NOT_FOUND, "Logins not found.", null);
		}
		return new ResponseObject(HttpStatus.OK, "Get all login successfully.", logins);
	}
}
