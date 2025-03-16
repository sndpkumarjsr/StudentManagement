package com.studentmanagement.util;

import com.studentmanagement.dto.GradeDto;
import com.studentmanagement.entity.Grade;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GradeMapperTest {

    private GradeMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new GradeMapper();
    }

    @Test
    public void checkToGrade(){
        GradeDto dto = new GradeDto("X","Batch 2024-2025");

        Grade grade = mapper.toGrade(dto);

        Assertions.assertEquals(grade.getName(),dto.name());
        Assertions.assertEquals(grade.getDescription(),dto.description());

    }

    @Test
    public void checkTogradeDto(){
        Grade grade = new Grade("X","Batch 2024-2025");

        GradeDto dto = mapper.toGradeDto(grade);

        Assertions.assertEquals(dto.name(),grade.getName());
        Assertions.assertEquals(dto.description(),grade.getDescription());
    }
}