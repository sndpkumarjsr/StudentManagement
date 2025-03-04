package com.studentmanagement.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record StudentDto(
        @NotEmpty(message = "First name should not be empty")
        String firstName,
        String lastName,
        @NotEmpty(message = "Email should not be empty")
        @Email(message = "Provide valid email")
        String email,
        int age,
        Integer schoolId
) {
}
