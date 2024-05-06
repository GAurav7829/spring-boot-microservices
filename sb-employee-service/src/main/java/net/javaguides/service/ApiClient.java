package net.javaguides.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import net.javaguides.dto.DepartmentDto;

//@FeignClient(url = "http://localhost:8080", value = "DEPARTMENT-SERVICE")
@FeignClient(name = "DEPARTMENT-SERVICE") // as we use eureka server
public interface ApiClient {
	// build get Department rest api
	@GetMapping("/api/departments/{departmentCode}")
	DepartmentDto getDepartment(@PathVariable String departmentCode);
}
