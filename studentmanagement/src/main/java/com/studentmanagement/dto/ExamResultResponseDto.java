package com.studentmanagement.dto;

import java.time.LocalDate;

public record ExamResultResponseDto(
    String examTypeName,
    double totalMarks,
    double passMarks,
    String examName,
    LocalDate startDate,
    double marks,
    String StudentName
) {
}
