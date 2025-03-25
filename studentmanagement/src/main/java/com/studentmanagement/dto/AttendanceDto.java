package com.studentmanagement.dto;

import com.studentmanagement.entity.ATTENDANCE_STATUS;

import java.time.LocalDate;

public record AttendanceDto(
        LocalDate date,
        ATTENDANCE_STATUS isPresent,
        String remarks,
        String admissionNumber
) {
}
