package com.parking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.parking.constant.ConfigConstant;
import com.parking.dto.EmployeeDto;
import com.parking.model.ResponseObject;
import com.parking.service.EmployeeService;

@RestController
@CrossOrigin(origins = ConfigConstant.urlFEConnectionString)
@RequestMapping("/api")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@PostMapping("/add/employee")
	public ResponseEntity<?> doAddEmployee(@RequestBody EmployeeDto employeeDto)
	{
		try {
			employeeService.add(employeeDto);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseObject(e.getMessage(), null));
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseObject("Employee was added successfully", null));
				
	}
}
