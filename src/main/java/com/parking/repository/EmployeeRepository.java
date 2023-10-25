package com.parking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.parking.dto.employee.EmployeeRequest;
import com.parking.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	Employee findByLoginId(Long id);
	
	Employee findByLoginUsername(String username);
	
	@Query(value = "SELECT MAX(id) FROM EMPLOYEES", nativeQuery = true)
	Long getMaxId();

	@Modifying(clearAutomatically = true)
	@Query(value ="INSERT INTO employees(firstName, lastName, gender, avatar, status, email, phone, parkingLotId) values (:#{#employee.firstName}, :#{#employee.lastName}, :#{#employee.gender}, :#{#employee.avatar},1, :#{#employee.email}, :#{#employee.phone}, :#{#employee.parkingLotId})", nativeQuery = true)
	void insert(@Param("employee") EmployeeRequest employeeDto);
	
	@Modifying(clearAutomatically = true)
	@Query(value = "UPDATE employees SET firstName = :#{#employee.firstName}, lastName = :#{#employee.lastName}, gender = :#{#employee.gender}, phone = :#{#employee.phone}, avatar = :#{#employee.avatar}, parkingLotId = :#{#employee.parkingLotId} WHERE id = :#{#employee.id}", nativeQuery = true)
	void update(@Param("employee") EmployeeRequest employee);
	
	@Query(value = "UPDATE employees SET loginId = ?2 WHERE id = ?1", nativeQuery = true)
	void setLogin(Long employeeId, Long loginId);
}
