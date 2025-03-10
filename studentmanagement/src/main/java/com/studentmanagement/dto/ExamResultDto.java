package com.studentmanagement.dto;

public record ExamResultDto(
        double marks,
        Integer examId,
        Integer studentId
) {
}
