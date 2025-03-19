package com.studentmanagement.dto;

import java.time.LocalDate;

public record TeacherResponseDto(
        String firstName,
        String lastName,
        String email,
        String password,
        LocalDate dateOfBirth,
        String phone,
        String courseName,
        String description
) {
}
