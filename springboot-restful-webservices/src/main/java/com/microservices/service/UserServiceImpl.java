package com.microservices.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.microservices.dto.UserDto;
import com.microservices.entity.User;
import com.microservices.exception.EmailAlreadyExistException;
import com.microservices.exception.ResourceNotFoundException;
import com.microservices.mapper.AutoUserMapper;
import com.microservices.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;

	@Override
	public UserDto createUser(UserDto userDto) {
		
		Optional<User> userbyEmail = userRepository.findByEmail(userDto.getEmail());
		if(userbyEmail.isPresent())
			throw new EmailAlreadyExistException("Email already Exist for User");
		
		// convert userDto to user jpa entity
//		User user = UserMapper.mapToUser(userDto);
		User user = AutoUserMapper.MAPPER.mapToUser(userDto);
		User savedUser = userRepository.save(user);
		// convert user jpa entity to userDto
//		UserDto savedUserDto = UserMapper.mapToUserDto(savedUser);
		UserDto savedUserDto = AutoUserMapper.MAPPER.mapToUserDto(savedUser);
		return savedUserDto;
	}

	@Override
	public UserDto getUserById(Long id) {
//		UserDto userDto = UserMapper.mapToUserDto(userRepository.findById(id).get());
		User userById = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "Id", id));
		UserDto userDto = AutoUserMapper.MAPPER.mapToUserDto(userById);
		return userDto;
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<User> users = userRepository.findAll();
//		return users.stream().map(user->UserMapper.mapToUserDto(user)).collect(Collectors.toList());
//		return users.stream().map(UserMapper::mapToUserDto).collect(Collectors.toList());
		return users.stream().map(AutoUserMapper.MAPPER::mapToUserDto).collect(Collectors.toList());
	}

	@Override
	public UserDto updateUser(UserDto user) {
		User existingUser = userRepository.findById(user.getId())
				.orElseThrow(() -> new ResourceNotFoundException("User", "Id", user.getId()));
		existingUser.setFirstName(user.getFirstName());
		existingUser.setLastName(user.getLastName());
		existingUser.setEmail(user.getEmail());
		User updatedUser = userRepository.save(existingUser);
//		return UserMapper.mapToUserDto(updatedUser);
		return AutoUserMapper.MAPPER.mapToUserDto(updatedUser);
	}

	@Override
	public void deleteUser(Long id) {
		userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User", "Id", id));
		userRepository.deleteById(id);
	}

}
