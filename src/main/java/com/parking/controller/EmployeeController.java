package com.parking.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.parking.constant.ConfigConstant;
import com.parking.dto.employee.EmployeeRequest;
import com.parking.model.ResponseObject;
import com.parking.service.EmployeeService;

@RestController
@CrossOrigin(origins = ConfigConstant.urlFEConnectionString)
@RequestMapping("/api/employees")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	@PostMapping("")
	public ResponseEntity<?> doAddEmployee(@RequestBody EmployeeRequest employeeDto) {
		ResponseObject responseObject = employeeService.add(employeeDto);
		return ResponseEntity.status(responseObject.getStatus()).body(responseObject);
	}

	@PutMapping("")
	public ResponseEntity<?> doEditEmployee(@RequestBody EmployeeRequest employee) {
		ResponseObject responseObject = employeeService.edit(employee);
		return ResponseEntity.status(responseObject.getStatus()).body(responseObject);
	}
	
	@GetMapping("/{username}")
	public ResponseEntity<?> doGetParkingLot(@PathVariable(name = "username") String username)
	{
		ResponseObject responseObject = employeeService.getParkingLot(username);
		return ResponseEntity.status(responseObject.getStatus()).body(responseObject);
	}
}
