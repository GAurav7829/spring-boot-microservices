package com.microservices.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.dto.UserDto;
import com.microservices.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
@Tag(name = "CRUD REST API for user resource", description = "Create User, get User, get all users, update user, delete user")
public class UserController {
	private UserService userService;

	@Operation(summary = "Create User REST Api", description = "It is used to save user to database")
	@ApiResponse(responseCode = "201", description = "Http Status 201 created")
	// create user rest api
	// http://localhost:8080/api/users/create
	@PostMapping("/create")
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto user) {
		UserDto savedUserDto = userService.createUser(user);
		return new ResponseEntity<UserDto>(savedUserDto, HttpStatus.CREATED);
	}

	@Operation(summary = "Get User by ID REST Api", description = "It is used to get single user from database")
	@ApiResponse(responseCode = "200", description = "Http Status 200 success")
	// get user by id
	// http://localhost:8080/api/users/1
	@GetMapping("/{id}")
	public ResponseEntity<UserDto> getUSerById(@PathVariable Long id) {
		UserDto userDto = userService.getUserById(id);
		return ResponseEntity.ok(userDto);
	}

	@Operation(summary = "Get all Users REST Api", description = "It is used to get all users from database")
	@ApiResponse(responseCode = "200", description = "Http Status 200 success")
	// get all users
	// http://localhost:8080/api/users
	@GetMapping
	public ResponseEntity<List<UserDto>> getAllUsers() {
		List<UserDto> users = userService.getAllUsers();
		return ResponseEntity.ok(users);
	}

	@Operation(summary = "Update User by ID REST Api", description = "It is used to update user from database")
	@ApiResponse(responseCode = "200", description = "Http Status 200 success")
	// update user
	// http://localhost:8080/api/users/1
	@PutMapping("/{id}")
	public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @Valid @RequestBody UserDto userDto) {
		userDto.setId(id);
		UserDto updatedUser = userService.updateUser(userDto);
		return ResponseEntity.ok(updatedUser);
	}

	@Operation(summary = "Delete User by ID REST Api", description = "It is used to delete user from database")
	@ApiResponse(responseCode = "200", description = "Http Status 200 success")
	// delete user
	// http://localhost:8080/api/users/1/delete
	@DeleteMapping("/{id}/delete")
	public ResponseEntity<String> deleteUser(@PathVariable Long id) {
		userService.deleteUser(id);
		return ResponseEntity.ok("User deleted successfully...");
	}

	// handle exception related to controller
//	@ExceptionHandler(ResourceNotFoundException.class)
//	public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFoundException exception,
//			WebRequest webRequest) {
//		ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(), exception.getMessage(),
//				webRequest.getDescription(false), "USER_NOT_FOUND");
//		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
//	}
}
