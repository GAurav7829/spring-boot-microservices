package net.javaguides.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.AllArgsConstructor;
import net.javaguides.dto.APIResponseDto;
import net.javaguides.dto.DepartmentDto;
import net.javaguides.dto.EmployeeDto;
import net.javaguides.entity.Employee;
import net.javaguides.mapper.AutoEmployeeMapper;
import net.javaguides.repository.EmployeeRepository;
import net.javaguides.service.ApiClient;
import net.javaguides.service.EmployeeService;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeRepository repository;
	private RestTemplate restTemplate;
	private WebClient webClient;
	private ApiClient feignClient;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeServiceImpl.class);

	@Override
	public EmployeeDto saveEmployee(EmployeeDto dto) {
//		Employee employee = new Employee(dto.getId(), dto.getFirstName(), dto.getLastName(), dto.getEmail());
		Employee employee = AutoEmployeeMapper.MAPPER.mapToUser(dto);
		Employee savedEmployee = repository.save(employee);
//		EmployeeDto savedDto = new EmployeeDto(savedEmployee.getId(), savedEmployee.getFirstName(),
//				savedEmployee.getLastName(), savedEmployee.getEmail());
		EmployeeDto savedDto = AutoEmployeeMapper.MAPPER.mapToUserDto(savedEmployee);
		return savedDto;
	}

	@Override
//	@CircuitBreaker(name = "${spring.application.name}", fallbackMethod = "getDefaultDepartment")
	@Retry(name = "${spring.application.name}", fallbackMethod = "getDefaultDepartment")
	public APIResponseDto getEmployeeById(Long employeeId) {
		LOGGER.info("inside getEmployeeById method");
		Employee employee = repository.findById(employeeId).get();
//		EmployeeDto dto = new EmployeeDto(employee.getId(), employee.getFirstName(), employee.getLastName(),
//				employee.getEmail());

		// REST Template Call code
//		ResponseEntity<DepartmentDto> responseEntity = restTemplate.getForEntity("http://localhost:8080/api/departments/"+employee.getDepartmentCode(), DepartmentDto.class);
//		DepartmentDto departmentDto = responseEntity.getBody();

		// WebClient REST API Call
		DepartmentDto departmentDto = webClient.get().uri("http://localhost:8080/api/departments/" + employee.getDepartmentCode()).retrieve()
				.bodyToMono(DepartmentDto.class).block();

		// REST API CALL using feign client
//		DepartmentDto departmentDto = feignClient.getDepartment(employee.getDepartmentCode());

		EmployeeDto dto = AutoEmployeeMapper.MAPPER.mapToUserDto(employee);

		APIResponseDto apiResponseDto = new APIResponseDto();
		apiResponseDto.setDepartment(departmentDto);
		apiResponseDto.setEmployee(dto);

		return apiResponseDto;
	}

	public APIResponseDto getDefaultDepartment(Long employeeId, Exception exception) {
		LOGGER.info("inside getDefaultDepartment method");
		Employee employee = repository.findById(employeeId).get();
		EmployeeDto dto = AutoEmployeeMapper.MAPPER.mapToUserDto(employee);
		
		DepartmentDto departmentDto = new DepartmentDto();
		departmentDto.setDepartmentName("R&D Department");
		departmentDto.setDepartmentCode("RD001");
		departmentDto.setDepartmentDescription("Research and Development Department");
		
		APIResponseDto apiResponseDto = new APIResponseDto();
		apiResponseDto.setDepartment(departmentDto);
		apiResponseDto.setEmployee(dto);
		return apiResponseDto;
	}
	
}
