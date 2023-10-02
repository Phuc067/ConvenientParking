package com.parking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.parking.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	Employee findByLoginId(Long id);
	
	@Query(value = "SELECT MAX(id) FROM EMPLOYEES", nativeQuery = true)
	Long getMaxId();

}
