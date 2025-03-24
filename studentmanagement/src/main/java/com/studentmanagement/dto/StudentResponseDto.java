package com.studentmanagement.dto;

import com.studentmanagement.entity.Guardian;

import java.time.LocalDate;

public record StudentResponseDto(
        String admissionNumber,
        String firstName,
        String lastName,
        String email,
        int age,
        LocalDate dateOfBirth,
        String phone,
        GuardianResponseDto guardian
) {
}
