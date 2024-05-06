package com.microservices.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.microservices.dto.UserDto;
import com.microservices.entity.User;

@Mapper
public interface AutoUserMapper {
	// both user and userDto have same fields names'
	
	AutoUserMapper MAPPER = Mappers.getMapper(AutoUserMapper.class);
	
//	@Mapping(source = "email", target = "email") // if field names are different
	UserDto mapToUserDto(User user);
	User mapToUser(UserDto user);
}