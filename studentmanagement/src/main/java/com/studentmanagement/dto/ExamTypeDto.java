package com.studentmanagement.dto;

public record ExamTypeDto(
        String examName,
        double totalMarks,
        double passMarks,
        String description
) {
}
