package com.studentmanagement.dto;

import java.time.LocalDate;

public record StudentResponseDto(
        String firstName,
        String lastName,
        String email,
        int age,
        LocalDate dateOfBirth,
        String phone
) {
}
