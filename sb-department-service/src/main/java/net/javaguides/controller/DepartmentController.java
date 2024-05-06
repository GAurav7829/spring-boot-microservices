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
import net.javaguides.dto.DepartmentDto;
import net.javaguides.service.DepartmentService;

@RestController
@RequestMapping("/api/departments")
@AllArgsConstructor
public class DepartmentController {
	
	private DepartmentService service;
	
	// build save department rest api
	@PostMapping
	public ResponseEntity<DepartmentDto> saveDepartment(@RequestBody DepartmentDto dto){
		DepartmentDto savedDepartment = service.saveDepartment(dto);
		return new ResponseEntity<DepartmentDto>(savedDepartment, HttpStatus.CREATED);
	}
	
	// build get Department rest api
	@GetMapping("/{departmentCode}")
	public ResponseEntity<DepartmentDto> getDepartment(@PathVariable String departmentCode){
		DepartmentDto dto = service.getDepartmentByCode(departmentCode);
		return new ResponseEntity<DepartmentDto>(dto, HttpStatus.OK);
	}
}
