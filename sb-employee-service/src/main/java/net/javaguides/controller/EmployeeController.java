package net.javaguides.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import net.javaguides.dto.APIResponseDto;
import net.javaguides.dto.EmployeeDto;
import net.javaguides.service.EmployeeService;

@RestController
@RequestMapping("/api/employees")
@AllArgsConstructor
public class EmployeeController {

	private EmployeeService service;
	
	// build save employee rest api
	@PostMapping
	public ResponseEntity<EmployeeDto> saveEmployee(@RequestBody EmployeeDto dto){
		EmployeeDto employeeDto = service.saveEmployee(dto);
		return new ResponseEntity<EmployeeDto>(employeeDto, HttpStatus.CREATED);
	}
	
	// build get employee by id
	@GetMapping("/{id}")
	public ResponseEntity<APIResponseDto> getEmployeeById(@PathVariable Long id){
		APIResponseDto dto = service.getEmployeeById(id);
		return ResponseEntity.ok(dto);
	}
}
