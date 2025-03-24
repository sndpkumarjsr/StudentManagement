package com.studentmanagement.dto;

import java.time.LocalDate;

public record GuardianDto(
        String firstName,
        String lastName,
        LocalDate dateOfBirth,
        String phone,
        String email,
        String password,
        String admissionNumber
) {
}
