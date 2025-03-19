package com.studentmanagement.dto;

import com.studentmanagement.entity.Course;

import java.time.LocalDate;

public record TeacherDto(
        String firstName,
        String lastName,
        String email,
        String password,
        LocalDate dateOfBirth,
        String phone,
        Integer courseId
) {
}
