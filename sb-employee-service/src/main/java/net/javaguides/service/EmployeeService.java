package net.javaguides.service;

import net.javaguides.dto.APIResponseDto;
import net.javaguides.dto.EmployeeDto;

public interface EmployeeService {
	EmployeeDto saveEmployee(EmployeeDto dto);
	APIResponseDto getEmployeeById(Long employeeId);
}
