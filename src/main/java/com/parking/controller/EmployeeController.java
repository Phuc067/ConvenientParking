package com.parking.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.parking.constant.ConfigConstant;
import com.parking.dto.auth.UsernameRequest;
import com.parking.dto.employee.EmployeeRequest;
import com.parking.model.ResponseObject;
import com.parking.service.EmployeeService;

@RestController
@CrossOrigin(origins = ConfigConstant.urlFEConnectionString)
@RequestMapping("/api/employee")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	@PostMapping("/add")
	public ResponseEntity<?> doAddEmployee(@RequestBody EmployeeRequest employeeDto) {
		ResponseObject responseObject = employeeService.add(employeeDto);
		return ResponseEntity.status(responseObject.getStatus()).body(responseObject);

	}

	@PostMapping("/edit")
	public ResponseEntity<?> doEditEmployee(@RequestBody EmployeeRequest employee) {
		ResponseObject responseObject = employeeService.edit(employee);
		return ResponseEntity.status(responseObject.getStatus()).body(responseObject);
	}
	
	@PostMapping("/parkinglot-by-username")
	public ResponseEntity<?> doGetParkingLot(@RequestBody UsernameRequest request)
	{
		ResponseObject responseObject = employeeService.getParkingLot(request.getUsername());
		return ResponseEntity.status(responseObject.getStatus()).body(responseObject);
	}
}
