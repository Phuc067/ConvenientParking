package com.parking.service;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;

import com.parking.dto.EmployeeRequest;
import com.parking.entity.Employee;
import com.parking.model.ResponseObject;

public interface EmployeeService {
	ResponseObject add(EmployeeRequest employeeDto);
	ResponseObject edit(EmployeeRequest employee);
	ResponseObject register(EmployeeRequest employeeDto, HttpSession session) throws MessagingException;
	ResponseObject getById(Long id);
	ResponseObject getParkingLot(String username);
}
