package com.studentmanagement.util;

import com.studentmanagement.dto.CourseDto;
import com.studentmanagement.dto.CourseResponseDto;
import com.studentmanagement.entity.Course;
import org.springframework.stereotype.Service;

@Service
public class CourseMapper {

    public Course toCourse(CourseDto dto){
        return new Course(dto.name(),dto.description());
    }

    public CourseResponseDto toCourseResponseDto(Course course){
        return  new CourseResponseDto(course.getName(),course.getDescription(),course.getGrade().getName());
    }

    public CourseDto toCourseDto(Course course){
        return new CourseDto(course.getName(),course.getDescription());
    }
}
