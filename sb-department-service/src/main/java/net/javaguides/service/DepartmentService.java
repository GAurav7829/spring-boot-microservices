package net.javaguides.service;

import net.javaguides.dto.DepartmentDto;

public interface DepartmentService {
	DepartmentDto saveDepartment(DepartmentDto dto);
	DepartmentDto getDepartmentByCode(String departmentCode);
}
