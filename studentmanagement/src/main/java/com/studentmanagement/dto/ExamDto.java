package com.studentmanagement.dto;

import java.time.LocalDate;

public record ExamDto(
        String name,
        LocalDate startDate,
        String examType_examName
) {
}
