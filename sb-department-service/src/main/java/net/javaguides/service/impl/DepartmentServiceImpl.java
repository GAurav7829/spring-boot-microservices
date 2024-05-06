package net.javaguides.service.impl;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import net.javaguides.dto.DepartmentDto;
import net.javaguides.entity.Department;
import net.javaguides.mapper.AutoDepartmentMapper;
import net.javaguides.repository.DepartmentRepository;
import net.javaguides.service.DepartmentService;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

	private DepartmentRepository repository;

	@Override
	public DepartmentDto saveDepartment(DepartmentDto dto) {
		// convert departmentDto to Department JPA entity
//		Department department = new Department(dto.getId(), dto.getDepartmentName(), dto.getDepartmentDescription(),
//				dto.getDepartmentCode());
		Department department = AutoDepartmentMapper.MAPPER.mapToDepartment(dto);

		Department savedDepartment = repository.save(department);

		// convert department JPA entity to dto
//		DepartmentDto savedDto = new DepartmentDto(savedDepartment.getId(), savedDepartment.getDepartmentName(),
//				savedDepartment.getDepartmentDescription(), savedDepartment.getDepartmentCode());
		DepartmentDto savedDto = AutoDepartmentMapper.MAPPER.mapToDepartmentDto(savedDepartment);
		
		return savedDto;
	}

	@Override
	public DepartmentDto getDepartmentByCode(String departmentCode) {
		Department department = repository.findByDepartmentCode(departmentCode);

//		DepartmentDto dto = new DepartmentDto(department.getId(), department.getDepartmentName(),
//				department.getDepartmentDescription(), department.getDepartmentCode());
		DepartmentDto dto = AutoDepartmentMapper.MAPPER.mapToDepartmentDto(department);

		return dto;
	}

}
