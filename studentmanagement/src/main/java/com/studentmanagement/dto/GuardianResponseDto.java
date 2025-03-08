package com.studentmanagement.dto;

import com.studentmanagement.entity.Student;

import java.time.LocalDate;
import java.util.List;

public record GuardianResponseDto(
        String firstName,
        String lastName,
        LocalDate dateOfBirth,
        String phone,
        String email,
        List<Student> students
) {
}
