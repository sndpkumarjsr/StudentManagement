package com.studentmanagement.dto;

public record ExamResultDto(
        double marks,
        String examName,
        String examTypeName,
        String admissionNumber,
        String courseName,
        String description
) {
}
