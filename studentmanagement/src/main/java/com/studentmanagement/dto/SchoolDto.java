package com.studentmanagement.dto;

import jakarta.validation.constraints.NotEmpty;

public record SchoolDto(
        @NotEmpty(message = "School name should not be empty")
        String name
) {
}
