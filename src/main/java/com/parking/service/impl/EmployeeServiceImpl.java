package com.parking.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parking.dto.EmployeeDto;
import com.parking.entity.Employee;
import com.parking.entity.ParkingLot;
import com.parking.repository.EmployeeRepository;
import com.parking.repository.ParkingLotRepository;
import com.parking.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired 
	private ParkingLotRepository parkingLotRepository;
	
	@Override
	public void add(EmployeeDto employeeDto) {
		Long id = employeeRepository.getMaxId();
		ParkingLot parkingLot = parkingLotRepository.findById(employeeDto.getParkingLotId()).get();
		Employee employee = new Employee();
		employee.setId(id+1);
		employee.setFirstName(employeeDto.getFirstName());
		employee.setLastName(employeeDto.getLastName());
		employee.setGender(employeeDto.getGender());
		employee.setEmail(employeeDto.getEmail());
		employee.setStatus(true);
		employee.setAvatar(employeeDto.getAvatar());
		employee.setParkingLot(parkingLot);
		employeeRepository.save(employee);
	}
	
}
