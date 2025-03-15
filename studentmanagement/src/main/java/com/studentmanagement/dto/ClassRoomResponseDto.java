package com.studentmanagement.dto;

import com.studentmanagement.entity.Status;
import com.studentmanagement.entity.Student;

import java.util.List;

public record ClassRoomResponseDto(
        String year,
        char section,
        Status status,
        String remarks,
        List<Student> students
) {
}
