package com.studentmanagement.util;

import com.studentmanagement.dto.CourseDto;
import com.studentmanagement.dto.CourseResponseDto;
import com.studentmanagement.entity.Course;
import com.studentmanagement.entity.Grade;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CourseMapperTest {

    private CourseMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new CourseMapper();
    }

    @Test
    public void checkToCourse(){
        CourseDto dto = new CourseDto("Science","NCERT Pattern");

        Course course = mapper.toCourse(dto);

        Assertions.assertEquals(course.getName(),dto.name());
        Assertions.assertEquals(course.getDescription(),dto.description());
    }

    @Test
    public void checkToCourseResponseDto(){
        Course course = new Course("Science","NCERT Pattern");
        course.setId(1);
        Grade grade = new Grade("X","Batch 2025-2026");
        grade.setId(1);
        course.setGrade(grade);

        CourseResponseDto responseDto = mapper.toCourseResponseDto(course);

        Assertions.assertEquals(responseDto.name(),course.getName());
        Assertions.assertEquals(responseDto.description(),course.getDescription());
        Assertions.assertEquals(responseDto.gradeName(),course.getGrade().getName());
    }
}