package net.javaguides.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import net.javaguides.dto.DepartmentDto;
import net.javaguides.entity.Department;

@Mapper
public interface AutoDepartmentMapper {
	AutoDepartmentMapper MAPPER = Mappers.getMapper(AutoDepartmentMapper.class);
	
	DepartmentDto mapToDepartmentDto(Department department);
	Department mapToDepartment(DepartmentDto dto);
}
