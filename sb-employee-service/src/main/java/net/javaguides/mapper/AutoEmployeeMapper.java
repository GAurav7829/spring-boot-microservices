package net.javaguides.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import net.javaguides.dto.EmployeeDto;
import net.javaguides.entity.Employee;

@Mapper
public interface AutoEmployeeMapper {
	AutoEmployeeMapper MAPPER = Mappers.getMapper(AutoEmployeeMapper.class);
	
	EmployeeDto mapToUserDto(Employee user);
	Employee mapToUser(EmployeeDto user);
}
