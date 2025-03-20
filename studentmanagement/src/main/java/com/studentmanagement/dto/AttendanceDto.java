package com.studentmanagement.dto;

import com.studentmanagement.entity.ATTENDANCE_STATUS;

public record AttendanceDto(
        ATTENDANCE_STATUS isPresent,
        String remarks,
        Integer studentId
) {
}
