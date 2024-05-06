package com.microservices.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "User DTO Model information")
public class UserDto {
	private Long id;
	@Schema(description = "User First Name")
	// first name should not be null or empty
	@NotEmpty(message = "firstName should not be null or empty")
	private String firstName;
	@Schema(description = "User Last Name")
	@NotEmpty(message = "lastName should not be null or empty")
	private String lastName;
	@Schema(description = "User Email Address")
	@NotEmpty(message = "email should not be null or empty")
	// should be valid email
	@Email(message = "email is not valid")
	private String email;
}
