package com.microservices.service;

import java.util.List;

import com.microservices.dto.UserDto;

public interface UserService {
	UserDto createUser(UserDto user);
	UserDto getUserById(Long id);
	List<UserDto> getAllUsers();
	UserDto updateUser(UserDto user);
	void deleteUser(Long id);
}
