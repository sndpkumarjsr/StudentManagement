package com.studentmanagement.dto;

import java.time.LocalDate;

public record ExamResponseDto(
        String name,
        LocalDate startDate,
        String examName,
        double totalMarks,
        double passMarks,
        String description
) {
}
