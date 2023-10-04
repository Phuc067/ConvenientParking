package com.parking.service;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;

import com.parking.dto.EmployeeDto;
import com.parking.entity.Employee;
import com.parking.model.ResponseObject;

public interface EmployeeService {
	ResponseObject add(EmployeeDto employeeDto);
	ResponseObject edit(EmployeeDto employee);
	ResponseObject register(EmployeeDto employeeDto, HttpSession session) throws MessagingException;
}
