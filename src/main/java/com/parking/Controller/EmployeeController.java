package com.parking.controller;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.parking.constant.ConfigConstant;
import com.parking.constant.SessionConstant;
import com.parking.dto.EmployeeRequest;
import com.parking.model.ResponseObject;
import com.parking.service.EmployeeService;
import com.parking.utils.VerifyCodeManager;

@RestController
@CrossOrigin(origins = ConfigConstant.urlFEConnectionString)
@RequestMapping("/api")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	@PostMapping("/add/employee")
	public ResponseEntity<?> doAddEmployee(@RequestBody EmployeeRequest employeeDto) {
		ResponseObject responseObject = employeeService.add(employeeDto);
		return ResponseEntity.status(responseObject.getStatus()).body(responseObject);

	}

	@PostMapping("edit/employee")
	public ResponseEntity<?> doEditEmployee(@RequestBody EmployeeRequest employee) {
		ResponseObject responseObject = employeeService.edit(employee);
		return ResponseEntity.status(responseObject.getStatus()).body(responseObject);
	}
	
	@PostMapping("register/employee")
	public ResponseEntity<?> doRegisterEmplyee(@RequestBody EmployeeRequest employeeDto, HttpSession session) throws MessagingException
	{
		ResponseObject responseObject = employeeService.register(employeeDto, session);
		return ResponseEntity.status(responseObject.getStatus()).body(responseObject);
		
	}
}
