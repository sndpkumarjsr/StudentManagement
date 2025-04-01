package com.studentmanagement.dto;

import com.studentmanagement.entity.Status;

public record ClassRoomDto(
        String classRoomNumber,
        String year,
        char section,
        Status status,
        String remarks
) {
}
