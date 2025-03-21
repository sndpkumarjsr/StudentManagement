package com.studentmanagement.dto;

import com.studentmanagement.entity.Guardian;

import java.time.LocalDate;

public record StudentResponseDto(
        String firstName,
        String lastName,
        String email,
        int age,
        LocalDate dateOfBirth,
        String phone,
        Guardian guardian
) {
}
