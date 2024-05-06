package com.microservices.mapper;

import com.microservices.dto.UserDto;
import com.microservices.entity.User;

public interface UserMapper {
	static UserDto mapToUserDto(User user) {
		UserDto userDto = new UserDto(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail());
		return userDto;
	}
	
	static User mapToUser(UserDto userDto) {
		User user = new User(userDto.getId(), userDto.getFirstName(), userDto.getLastName(), userDto.getEmail());
		return user;
	}
}